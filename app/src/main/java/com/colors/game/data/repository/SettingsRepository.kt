package com.colors.game.data.repository

import com.colors.game.data.local.DataStoreManager
import com.colors.game.data.model.AppSettings
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsRepository @Inject constructor(
    private val dataStore: DataStoreManager
) {
    val settingsFlow: Flow<AppSettings> = dataStore.settingsFlow
    suspend fun save(settings: AppSettings) = dataStore.saveSettings(settings)
}
