package com.colors.game.presentation.viewmodel

import android.app.Activity
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.colors.game.data.model.*
import com.colors.game.data.remote.AdManager
import com.colors.game.data.repository.DailyPuzzleRepository
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

data class DailyGameUiState(
    val gameState: GameState? = null,
    val settings: AppSettings = AppSettings(),
    val animatingCells: Map<Position, Int> = emptyMap(),
    val showPauseMenu: Boolean = false,
    val showResultDialog: Boolean = false,
    val isLoading: Boolean = true,
    val showAd: Boolean = false,
    val adTrigger: AdTrigger = AdTrigger.NONE,
    val dateKey: String = "",
    val isToday: Boolean = false
)

@HiltViewModel
class DailyGameViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val dailyRepo: DailyPuzzleRepository,
    private val settingsRepo: SettingsRepository,
    private val adManager: AdManager
) : ViewModel() {

    val dateKey: String = checkNotNull(savedStateHandle["dateKey"])

    private val _uiState = MutableStateFlow(DailyGameUiState(dateKey = dateKey))
    val uiState: StateFlow<DailyGameUiState> = _uiState.asStateFlow()

    private var timerJob: Job? = null
    private var timeLimitAdShown = false
    private var isFirstDailyOfDay = false

    init {
        loadGame()
        observeSettings()
    }

    // ── Initialization ────────────────────────────────────────────────────

    private fun loadGame() {
        viewModelScope.launch {
            val isToday = dateKey == dailyRepo.todayKey()
            // Check if this is the first time playing today's daily
            val records = dailyRepo.recordsFlow.first()
            isFirstDailyOfDay = isToday && !records.containsKey(dateKey)

            val level = withContext(Dispatchers.Default) {
                dailyRepo.generateLevelForDate(dateKey)
            }
            val gameState = GameState(
                levelId            = level.id,
                difficulty         = level.difficulty,
                rows               = level.rows,
                cols               = level.cols,
                currentCells       = level.initialCells,
                movesRemaining     = level.maxMoves,
                timeElapsedSeconds = 0,
                timeLimitSeconds   = level.timeLimitSeconds,
                maxMoves           = level.maxMoves,
                optimalMoves       = level.optimalMoves,
                colorPalette       = level.colorPalette
            )
            val initialGroup = selectTopLeft(gameState.currentCells, gameState.rows, gameState.cols).toList()

            _uiState.update {
                it.copy(
                    gameState = gameState.copy(selectedGroup = initialGroup),
                    isLoading = false,
                    isToday   = isToday,
                    // Show ad before the first daily puzzle of the day
                    showAd    = isFirstDailyOfDay,
                    adTrigger = if (isFirstDailyOfDay) AdTrigger.DAILY_START else AdTrigger.NONE
                )
            }
        }
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

                val newState = state.copy(timeElapsedSeconds = state.timeElapsedSeconds + 1)
                _uiState.update { it.copy(gameState = newState) }

                if (!timeLimitAdShown && newState.timeElapsedSeconds >= AdManager.SECONDS_PER_AD) {
                    timeLimitAdShown = true
                    _uiState.update { it.copy(showAd = true, adTrigger = AdTrigger.TIME_LIMIT) }
                }
            }
        }
    }

    private fun stopTimer() { timerJob?.cancel(); timerJob = null }

    // ── User interactions ─────────────────────────────────────────────────

    private fun selectTopLeft(cells: List<GameColor>, rows: Int, cols: Int): Set<Position> =
        FloodFillEngine.getConnectedGroup(cells, rows, cols, 0, 0)

    fun applyColor(newColor: GameColor) {
        val state = _uiState.value.gameState ?: return
        if (state.isOver || state.isPaused) return

        val groupSet = selectTopLeft(state.currentCells, state.rows, state.cols)
        val currentGroupColor = state.currentCells[0]
        if (currentGroupColor == newColor) return

        if (timerJob == null || timerJob?.isActive == false) startTimer()

        val distMap = FloodFillEngine.bfsDistanceMap(state.rows, state.cols, groupSet)
        _uiState.update { it.copy(animatingCells = distMap) }

        val (newCells, _) = FloodFillEngine.applyColorChange(
            state.currentCells, state.rows, state.cols, groupSet, newColor
        )
        val newActiveGroup = selectTopLeft(newCells, state.rows, state.cols)
        val newMovesRemaining = state.movesRemaining - 1
        val isCompleted = FloodFillEngine.isGridComplete(newCells)
        val isFailed    = !isCompleted && newMovesRemaining <= 0

        val newState = state.copy(
            currentCells   = newCells,
            movesRemaining = newMovesRemaining,
            selectedGroup  = newActiveGroup.toList(),
            isCompleted    = isCompleted,
            isFailed       = isFailed
        )
        _uiState.update { it.copy(gameState = newState) }

        if (isCompleted || isFailed) {
            stopTimer()
            viewModelScope.launch {
                delay(600)
                saveResult(newState)
                if (newState.isCompleted) {
                    // For calendar daily puzzles (not today's first), use the 5-level ad counter
                    if (!isFirstDailyOfDay) {
                        _uiState.update { it.copy(showAd = true, adTrigger = AdTrigger.LEVEL_COMPLETED) }
                    } else {
                        _uiState.update { it.copy(showResultDialog = true) }
                    }
                } else {
                    _uiState.update { it.copy(showResultDialog = true) }
                }
            }
        } else {
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
    }

    fun restartLevel() {
        stopTimer()
        timeLimitAdShown = false
        viewModelScope.launch {
            val level = withContext(Dispatchers.Default) {
                dailyRepo.generateLevelForDate(dateKey)
            }
            val fresh = GameState(
                levelId            = level.id,
                difficulty         = level.difficulty,
                rows               = level.rows,
                cols               = level.cols,
                currentCells       = level.initialCells,
                movesRemaining     = level.maxMoves,
                timeElapsedSeconds = 0,
                timeLimitSeconds   = level.timeLimitSeconds,
                maxMoves           = level.maxMoves,
                optimalMoves       = level.optimalMoves,
                colorPalette       = level.colorPalette
            )
            val initialGroup = selectTopLeft(fresh.currentCells, fresh.rows, fresh.cols).toList()
            _uiState.update {
                it.copy(
                    gameState        = fresh.copy(selectedGroup = initialGroup),
                    showPauseMenu    = false,
                    showResultDialog = false,
                    showAd           = false
                )
            }
        }
    }

    fun onBackground() {
        val state = _uiState.value.gameState ?: return
        stopTimer()
        _uiState.update { it.copy(gameState = state.copy(isPaused = true)) }
    }

    fun onForeground() {
        val state = _uiState.value.gameState ?: return
        if (!state.isOver) startTimer()
    }

    fun requestAd(activity: Activity): Boolean {
        val trigger = _uiState.value.adTrigger
        _uiState.update { it.copy(showAd = false) }

        val onFinished: () -> Unit = {
            when (trigger) {
                AdTrigger.LEVEL_COMPLETED ->
                    _uiState.update { it.copy(showResultDialog = true) }
                AdTrigger.DAILY_START -> Unit  // ad shown before the game starts — just let the user play
                AdTrigger.TIME_LIMIT  -> Unit  // game continues after time-limit ad
                AdTrigger.NONE        -> Unit
            }
        }

        return when (trigger) {
            AdTrigger.DAILY_START,
            AdTrigger.LEVEL_COMPLETED -> adManager.onLevelCompleted(activity, onFinished)
            AdTrigger.TIME_LIMIT      -> adManager.onTimeLimitReached(activity, onFinished)
            AdTrigger.NONE            -> false
        }.also { shown -> if (!shown) onFinished() }
    }

    private suspend fun saveResult(state: GameState) {
        val stars     = state.computeStars()
        val movesUsed = state.maxMoves - state.movesRemaining
        dailyRepo.saveRecord(
            DailyPuzzleRecord(
                dateKey     = dateKey,
                isCompleted = state.isCompleted,
                stars       = stars,
                movesUsed   = movesUsed,
                timeSeconds = state.timeElapsedSeconds
            )
        )
    }
}
