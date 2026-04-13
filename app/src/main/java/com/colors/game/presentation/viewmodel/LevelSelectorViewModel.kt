package com.colors.game.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.colors.game.data.model.Difficulty
import com.colors.game.data.model.Level
import com.colors.game.data.model.LevelProgress
import com.colors.game.data.repository.GameStateRepository
import com.colors.game.data.repository.LevelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class LevelItem(
    val level: Level,
    val progress: LevelProgress?,
    val isUnlocked: Boolean
)

data class LevelSelectorUiState(
    val items: List<LevelItem> = emptyList(),
    val selectedDifficulty: Difficulty = Difficulty.EASY,
    val isLoading: Boolean = true
)

@HiltViewModel
class LevelSelectorViewModel @Inject constructor(
    private val levelRepo: LevelRepository,
    private val gameStateRepo: GameStateRepository
) : ViewModel() {

    private val _difficulty = MutableStateFlow(Difficulty.EASY)
    private val _uiState    = MutableStateFlow(LevelSelectorUiState())
    val uiState: StateFlow<LevelSelectorUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            // Wait until the pre-generated level cache is fully loaded.
            // On subsequent launches this resolves in <100ms (file read).
            // On first launch it waits for the one-time background generation.
            levelRepo.isReady.filter { it }.first()

            combine(_difficulty, gameStateRepo.progressMapFlow) { diff, progressMap ->
                diff to progressMap
            }.collectLatest { (diff, progressMap) ->
                _uiState.update { it.copy(isLoading = true, selectedDifficulty = diff) }
                // buildItems is now just HashMap lookups — fast, but keep off main thread
                val items = withContext(Dispatchers.Default) { buildItems(diff, progressMap) }
                _uiState.update { it.copy(items = items, isLoading = false) }
            }
        }
    }

    fun selectDifficulty(difficulty: Difficulty) {
        _difficulty.value = difficulty
    }

    private fun buildItems(
        difficulty: Difficulty,
        progressMap: Map<Int, LevelProgress>
    ): List<LevelItem> {
        val range = when (difficulty) {
            Difficulty.EASY   -> 1..100
            Difficulty.MEDIUM -> 101..200
            Difficulty.HARD   -> 201..500
        }
        return range.map { id ->
            val level    = levelRepo.getLevel(id)
            val progress = progressMap[id]
            val isUnlocked = id == range.first ||
                progressMap[id - 1]?.isCompleted == true ||
                (difficulty == Difficulty.EASY   && id == 1) ||
                (difficulty == Difficulty.MEDIUM && id == 101 && progressMap[100]?.isCompleted == true) ||
                (difficulty == Difficulty.HARD   && id == 201 && progressMap[200]?.isCompleted == true)
            LevelItem(level, progress, isUnlocked)
        }
    }
}
