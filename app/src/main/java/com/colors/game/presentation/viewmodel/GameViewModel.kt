package com.colors.game.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.colors.game.data.model.*
import com.colors.game.data.repository.GameStateRepository
import com.colors.game.data.repository.LevelRepository
import com.colors.game.data.repository.SettingsRepository
import com.colors.game.domain.FloodFillEngine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
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
    val isLoading: Boolean = true
)

@HiltViewModel
class GameViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val levelRepo: LevelRepository,
    private val gameStateRepo: GameStateRepository,
    private val settingsRepo: SettingsRepository
) : ViewModel() {

    private val levelId: Int = checkNotNull(savedStateHandle["levelId"])

    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    private var timerJob: Job? = null

    init {
        loadGame()
        observeSettings()
    }

    // ── Initialization ────────────────────────────────────────────────────

    private fun loadGame() {
        viewModelScope.launch {
            // First, check if there's a saved game for this level
            val saved = gameStateRepo.activeGameFlow.firstOrNull()
            val gameState = if (saved != null && saved.levelId == levelId) {
                saved
            } else {
                buildFreshGameState(levelId)
            }
            _uiState.update { it.copy(gameState = gameState, isLoading = false) }

            // Auto-select the group at (0,0) to hint the player
            if (!gameState.isOver) {
                selectCell(0, 0)
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

                val newElapsed = state.timeElapsedSeconds + 1
                val timedOut = newElapsed >= state.timeLimitSeconds

                val newState = state.copy(
                    timeElapsedSeconds = newElapsed,
                    isFailed = state.isFailed || timedOut
                )
                _uiState.update { it.copy(gameState = newState) }
                autoSave(newState)

                if (timedOut) {
                    _uiState.update { it.copy(showResultDialog = true) }
                    break
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
     * Called when the player taps a cell.
     * Performs flood-fill from that position to highlight the connected group.
     */
    fun selectCell(row: Int, col: Int) {
        val state = _uiState.value.gameState ?: return
        if (state.isOver || state.isPaused) return

        val group = FloodFillEngine.getConnectedGroup(
            state.currentCells, state.rows, state.cols, row, col
        )

        val newState = state.copy(selectedGroup = group.toList())
        _uiState.update { it.copy(gameState = newState) }

        // Start timer on first interaction
        if (timerJob == null || timerJob?.isActive == false) {
            startTimer()
        }
    }

    /**
     * Called when the player picks a color from the color picker.
     * Only acts if a group is currently selected and the color is different.
     */
    fun applyColor(newColor: GameColor) {
        val state = _uiState.value.gameState ?: return
        if (state.isOver || state.isPaused) return
        if (state.selectedGroup.isEmpty()) return

        val currentGroupColor = state.currentCells[
            state.selectedGroup.first().row * state.cols + state.selectedGroup.first().col
        ]
        if (currentGroupColor == newColor) return // no-op: same color

        val groupSet = state.selectedGroup.toSet()

        // Compute animation distances for ripple effect
        val distMap = FloodFillEngine.bfsDistanceMap(state.rows, state.cols, groupSet)
        _uiState.update { it.copy(animatingCells = distMap) }

        // Apply color change and expansion
        val (newCells, expandedGroup) = FloodFillEngine.applyColorChange(
            state.currentCells, state.rows, state.cols, groupSet, newColor
        )

        val newMovesRemaining = state.movesRemaining - 1
        val isCompleted = FloodFillEngine.isGridComplete(newCells)
        val isFailed = !isCompleted && newMovesRemaining <= 0

        val newState = state.copy(
            currentCells = newCells,
            movesRemaining = newMovesRemaining,
            selectedGroup = expandedGroup.toList(),
            isCompleted = isCompleted,
            isFailed = isFailed
        )
        _uiState.update { it.copy(gameState = newState) }

        if (isCompleted || isFailed) {
            stopTimer()
            viewModelScope.launch {
                delay(600) // let animation play
                _uiState.update { it.copy(showResultDialog = true) }
                recordResult(newState)
                gameStateRepo.clearActiveGame()
            }
        } else {
            autoSave(newState)
            // Clear animation map after the wave finishes
            viewModelScope.launch {
                delay(500)
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
        viewModelScope.launch {
            gameStateRepo.clearActiveGame()
            val fresh = buildFreshGameState(levelId)
            _uiState.update {
                it.copy(gameState = fresh, showPauseMenu = false, showResultDialog = false)
            }
            selectCell(0, 0)
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

    private suspend fun recordResult(state: GameState) {
        val stars = state.computeStars()
        val movesUsed = state.maxMoves - state.movesRemaining
        gameStateRepo.recordProgress(
            LevelProgress(
                levelId = state.levelId,
                isCompleted = state.isCompleted,
                stars = stars,
                bestMoves = if (state.isCompleted) movesUsed else null,
                bestTimeSeconds = if (state.isCompleted) state.timeElapsedSeconds else null
            )
        )
    }
}
