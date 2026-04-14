package com.colors.game.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.colors.game.data.model.*
import com.colors.game.data.remote.AdManager
import com.colors.game.data.remote.LeaderboardRepository
import com.colors.game.data.repository.GameStateRepository
import com.colors.game.data.repository.LevelRepository
import com.colors.game.data.repository.SettingsRepository
import com.colors.game.domain.FloodFillEngine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

// ──────────────────────────────────────────────────────────────────────────────
// UI state exposed to the screen
// ──────────────────────────────────────────────────────────────────────────────
data class GameUiState(
    val gameState: GameState? = null,
    val settings: AppSettings = AppSettings(),
    val animatingCells: Map<Position, Int> = emptyMap(), // position → wave distance
    val showPauseMenu: Boolean = false,
    val showResultDialog: Boolean = false,
    val showLeaderboard: Boolean = false,
    val isLoading: Boolean = true,
    /** Set to true by the ViewModel when an interstitial should be shown. */
    val showAd: Boolean = false,
    /** Identifies what should happen after the ad is dismissed. */
    val adTrigger: AdTrigger = AdTrigger.NONE
)

enum class AdTrigger { NONE, LEVEL_COMPLETED, TIME_LIMIT }

@HiltViewModel
class GameViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val levelRepo: LevelRepository,
    private val gameStateRepo: GameStateRepository,
    private val settingsRepo: SettingsRepository,
    private val leaderboardRepo: LeaderboardRepository,
    private val adManager: AdManager
) : ViewModel() {

    private val levelId: Int = checkNotNull(savedStateHandle["levelId"])

    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    private var timerJob: Job? = null
    /** Prevents the time-limit ad from firing more than once per level session. */
    private var timeLimitAdShown = false

    init {
        loadGame()
        observeSettings()
    }

    // ── Initialization ────────────────────────────────────────────────────

    private fun loadGame() {
        viewModelScope.launch {
            // Check if there's a saved game for this level
            val saved = gameStateRepo.activeGameFlow.firstOrNull()
            val gameState = if (saved != null && saved.levelId == levelId) {
                // Always unpause on load — the game was saved while paused when
                // the user navigated away, so isPaused=true would block applyColor().
                saved.copy(isPaused = false)
            } else {
                // Run off the main thread — on repeat launches this is an O(1)
                // cache lookup; on first-ever launch it generates just this one level.
                withContext(Dispatchers.Default) { buildFreshGameState(levelId) }
            }
            // Select the initial group at (0,0)
            val initialGroup = if (!gameState.isOver) {
                selectTopLeft(gameState.currentCells, gameState.rows, gameState.cols).toList()
            } else emptyList()

            _uiState.update {
                it.copy(
                    gameState = gameState.copy(selectedGroup = initialGroup),
                    isLoading = false
                )
            }
        }
    }

    private fun buildFreshGameState(id: Int): GameState {
        val level = levelRepo.getLevel(id)
        return GameState(
            levelId = level.id,
            difficulty = level.difficulty,
            rows = level.rows,
            cols = level.cols,
            currentCells = level.initialCells,
            movesRemaining = level.maxMoves,
            timeElapsedSeconds = 0,
            timeLimitSeconds = level.timeLimitSeconds,
            maxMoves = level.maxMoves,
            optimalMoves = level.optimalMoves,
            colorPalette = level.colorPalette
        )
    }

    private fun observeSettings() {
        viewModelScope.launch {
            settingsRepo.settingsFlow.collect { settings ->
                _uiState.update { it.copy(settings = settings) }
            }
        }
    }

    // ── Timer ─────────────────────────────────────────────────────────────

    private fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (true) {
                delay(1_000L)
                val state = _uiState.value.gameState ?: break
                if (state.isPaused || state.isOver) break

                // Cronómetro ascendente: solo cuenta el tiempo transcurrido.
                // No hay derrota por tiempo; el nivel solo falla al agotar movimientos.
                val newState = state.copy(timeElapsedSeconds = state.timeElapsedSeconds + 1)
                _uiState.update { it.copy(gameState = newState) }
                autoSave(newState)

                // Time-limit ad trigger: 5 minutes in a single level
                if (!timeLimitAdShown &&
                    newState.timeElapsedSeconds >= AdManager.SECONDS_PER_AD) {
                    timeLimitAdShown = true
                    _uiState.update { it.copy(showAd = true, adTrigger = AdTrigger.TIME_LIMIT) }
                }
            }
        }
    }

    private fun stopTimer() {
        timerJob?.cancel()
        timerJob = null
    }

    // ── User interactions ─────────────────────────────────────────────────

    /**
     * Selecciona el grupo conectado a (0,0). Solo uso interno.
     * La celda de inicio es siempre la esquina superior izquierda.
     */
    private fun selectTopLeft(cells: List<GameColor>, rows: Int, cols: Int): Set<Position> =
        FloodFillEngine.getConnectedGroup(cells, rows, cols, 0, 0)

    /**
     * Called when the player picks a color from the color picker.
     * El grupo activo es siempre el conectado a (0,0).
     */
    fun applyColor(newColor: GameColor) {
        val state = _uiState.value.gameState ?: return
        if (state.isOver || state.isPaused) return

        // El grupo activo parte siempre de (0,0)
        val groupSet = selectTopLeft(state.currentCells, state.rows, state.cols)

        val currentGroupColor = state.currentCells[0] // color en (0,0)
        if (currentGroupColor == newColor) return      // mismo color, no-op

        // Arrancar el cronómetro con el primer movimiento
        if (timerJob == null || timerJob?.isActive == false) {
            startTimer()
        }

        // Compute animation distances for ripple effect
        val distMap = FloodFillEngine.bfsDistanceMap(state.rows, state.cols, groupSet)
        _uiState.update { it.copy(animatingCells = distMap) }

        // Apply color change and expansion
        val (newCells, _) = FloodFillEngine.applyColorChange(
            state.currentCells, state.rows, state.cols, groupSet, newColor
        )

        // El nuevo grupo activo es siempre el conectado a (0,0) tras el cambio
        val newActiveGroup = selectTopLeft(newCells, state.rows, state.cols)

        val newMovesRemaining = state.movesRemaining - 1
        val isCompleted = FloodFillEngine.isGridComplete(newCells)
        val isFailed = !isCompleted && newMovesRemaining <= 0

        val newState = state.copy(
            currentCells = newCells,
            movesRemaining = newMovesRemaining,
            selectedGroup = newActiveGroup.toList(),
            isCompleted = isCompleted,
            isFailed = isFailed
        )
        _uiState.update { it.copy(gameState = newState) }

        if (isCompleted || isFailed) {
            stopTimer()
            viewModelScope.launch {
                delay(600) // let animation play
                recordResult(newState)
                gameStateRepo.clearActiveGame()
                // Completion ad trigger: show ad every N completed levels
                if (newState.isCompleted) {
                    _uiState.update { it.copy(showAd = true, adTrigger = AdTrigger.LEVEL_COMPLETED) }
                } else {
                    _uiState.update { it.copy(showResultDialog = true) }
                }
            }
        } else {
            viewModelScope.launch {
                autoSave(newState)
                delay(500) // esperar a que termine la animación de ola
                _uiState.update { it.copy(animatingCells = emptyMap()) }
            }
        }
    }

    fun togglePause() {
        val state = _uiState.value.gameState ?: return
        val paused = !state.isPaused
        val newState = state.copy(isPaused = paused)
        _uiState.update { it.copy(gameState = newState, showPauseMenu = paused) }
        if (paused) stopTimer() else startTimer()
        viewModelScope.launch { autoSave(newState) }
    }

    fun restartLevel() {
        stopTimer()
        timerJob = null
        timeLimitAdShown = false
        viewModelScope.launch {
            gameStateRepo.clearActiveGame()
            val fresh = buildFreshGameState(levelId)
            val initialGroup = selectTopLeft(fresh.currentCells, fresh.rows, fresh.cols).toList()
            _uiState.update {
                it.copy(
                    gameState = fresh.copy(selectedGroup = initialGroup),
                    showPauseMenu = false,
                    showResultDialog = false,
                    showAd = false
                )
            }
        }
    }

    // ── Lifecycle ─────────────────────────────────────────────────────────

    /** Called from the screen's DisposableEffect / onStop lifecycle */
    fun onBackground() {
        val state = _uiState.value.gameState ?: return
        stopTimer()
        val paused = state.copy(isPaused = true)
        viewModelScope.launch { gameStateRepo.saveActiveGame(paused) }
    }

    fun onForeground() {
        val state = _uiState.value.gameState ?: return
        if (!state.isOver) startTimer()
    }

    override fun onCleared() {
        super.onCleared()
        val state = _uiState.value.gameState ?: return
        viewModelScope.launch { gameStateRepo.saveActiveGame(state) }
    }

    // ── Internal helpers ──────────────────────────────────────────────────

    private suspend fun autoSave(state: GameState) {
        if (!state.isOver) gameStateRepo.saveActiveGame(state)
    }

    fun showLeaderboard()    = _uiState.update { it.copy(showLeaderboard = true) }
    fun dismissLeaderboard() = _uiState.update { it.copy(showLeaderboard = false) }

    /**
     * Called by the Screen to actually show the interstitial via AdManager.
     * Returns true if the ad was shown (false = not loaded yet, proceed immediately).
     */
    fun requestAd(activity: android.app.Activity): Boolean {
        val trigger = _uiState.value.adTrigger
        _uiState.update { it.copy(showAd = false) }   // consume the flag

        val onFinished = {
            when (trigger) {
                AdTrigger.LEVEL_COMPLETED ->
                    _uiState.update { it.copy(showResultDialog = true) }
                AdTrigger.TIME_LIMIT ->
                    Unit // just resume — the game continues after the ad
                AdTrigger.NONE -> Unit
            }
        }

        return when (trigger) {
            AdTrigger.LEVEL_COMPLETED -> adManager.onLevelCompleted(activity, onFinished)
            AdTrigger.TIME_LIMIT      -> adManager.onTimeLimitReached(activity, onFinished)
            AdTrigger.NONE            -> false
        }.also { adShown ->
            // If no ad was available, proceed immediately
            if (!adShown) onFinished()
        }
    }

    private suspend fun recordResult(state: GameState) {
        val stars     = state.computeStars()
        val movesUsed = state.maxMoves - state.movesRemaining
        gameStateRepo.recordProgress(
            LevelProgress(
                levelId         = state.levelId,
                isCompleted     = state.isCompleted,
                stars           = stars,
                bestMoves       = if (state.isCompleted) movesUsed else null,
                bestTimeSeconds = if (state.isCompleted) state.timeElapsedSeconds else null
            )
        )
        // Submit scores to the leaderboard (only when the level was completed)
        if (state.isCompleted) {
            leaderboardRepo.submitScore(state.levelId, LeaderboardType.MOVES, movesUsed)
            leaderboardRepo.submitScore(state.levelId, LeaderboardType.TIME,  state.timeElapsedSeconds)
        }
    }
}
