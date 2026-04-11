package com.colors.game.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.colors.game.data.model.LevelProgress
import com.colors.game.data.model.GameState
import com.colors.game.data.repository.GameStateRepository
import com.colors.game.domain.LevelGenerator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MainMenuUiState(
    val nextAvailableLevelId: Int = 1,
    val resumableLevelId: Int? = null,   // in-progress level
    val totalCompleted: Int = 0
)

@HiltViewModel
class MainMenuViewModel @Inject constructor(
    private val gameStateRepo: GameStateRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainMenuUiState())
    val uiState: StateFlow<MainMenuUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                gameStateRepo.progressMapFlow,
                gameStateRepo.activeGameFlow
            ) { progressMap, activeGame ->
                val completed = progressMap.values.count { it.isCompleted }
                val nextId = (progressMap.keys.maxOrNull() ?: 0) + 1
                val next = nextId.coerceIn(1, LevelGenerator.TOTAL_LEVELS)

                MainMenuUiState(
                    nextAvailableLevelId = next,
                    resumableLevelId = activeGame?.takeIf { !it.isOver }?.levelId,
                    totalCompleted = completed
                )
            }.collect { _uiState.value = it }
        }
    }
}
