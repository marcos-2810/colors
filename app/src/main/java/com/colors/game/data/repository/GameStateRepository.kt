package com.colors.game.data.repository

import com.colors.game.data.local.DataStoreManager
import com.colors.game.data.model.GameState
import com.colors.game.data.model.LevelProgress
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameStateRepository @Inject constructor(
    private val dataStore: DataStoreManager
) {
    val activeGameFlow: Flow<GameState?> = dataStore.activeGameFlow
    val progressMapFlow: Flow<Map<Int, LevelProgress>> = dataStore.progressMapFlow

    suspend fun saveActiveGame(state: GameState) = dataStore.saveActiveGame(state)
    suspend fun clearActiveGame() = dataStore.clearActiveGame()
    suspend fun recordProgress(progress: LevelProgress) = dataStore.updateLevelProgress(progress)
}
