package com.colors.game.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.colors.game.data.repository.GameStateRepository
import com.colors.game.data.repository.SettingsRepository
import com.colors.game.domain.LevelGenerator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MainMenuUiState(
    val nextLevelId: Int = 1,
    val hasNeverPlayed: Boolean = true,
    val resumableLevelId: Int? = null,
    val totalCompleted: Int = 0,
    val tutorialCompleted: Boolean = false,
    /** 0..1 fraction of board covered in the active game for [nextLevelId]. Null if no game in progress. */
    val activeGameCoverage: Float? = null
)

@HiltViewModel
class MainMenuViewModel @Inject constructor(
    private val gameStateRepo: GameStateRepository,
    private val settingsRepo: SettingsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainMenuUiState())
    val uiState: StateFlow<MainMenuUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                gameStateRepo.progressMapFlow,
                gameStateRepo.activeGameFlow,
                settingsRepo.settingsFlow
            ) { progressMap, activeGame, settings ->

                val completed     = progressMap.values.count { it.isCompleted }
                val hasEverPlayed = progressMap.isNotEmpty() || activeGame != null

                // Siguiente nivel = el primero que no esté completado (o el siguiente al máximo)
                val nextId = if (progressMap.isEmpty()) {
                    1
                } else {
                    val maxCompleted = progressMap.values
                        .filter { it.isCompleted }
                        .maxOfOrNull { it.levelId } ?: 0
                    (maxCompleted + 1).coerceAtMost(LevelGenerator.TOTAL_LEVELS)
                }

                // Board coverage of the active game if it belongs to the next level
                val coverage = activeGame
                    ?.takeIf { !it.isOver && it.levelId == nextId && it.rows * it.cols > 0 }
                    ?.let { game ->
                        (game.selectedGroup.size.toFloat() / (game.rows * game.cols))
                            .coerceIn(0f, 1f)
                    }

                MainMenuUiState(
                    nextLevelId       = nextId,
                    hasNeverPlayed    = !hasEverPlayed,
                    resumableLevelId  = activeGame?.takeIf { !it.isOver }?.levelId,
                    totalCompleted    = completed,
                    tutorialCompleted = settings.tutorialCompleted,
                    activeGameCoverage = coverage
                )
            }.collect { _uiState.value = it }
        }
    }

    /** Marcar el tutorial como completado y persistirlo */
    fun markTutorialCompleted() {
        viewModelScope.launch {
            val current = settingsRepo.settingsFlow.first()
            settingsRepo.save(current.copy(tutorialCompleted = true))
        }
    }
}
