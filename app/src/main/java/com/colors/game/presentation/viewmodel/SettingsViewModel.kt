package com.colors.game.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.colors.game.data.model.AppSettings
import com.colors.game.data.model.Language
import com.colors.game.data.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepo: SettingsRepository
) : ViewModel() {

    val settings = settingsRepo.settingsFlow.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        AppSettings()
    )

    fun setDaltonicMode(enabled: Boolean) = update { it.copy(daltonicMode = enabled) }
    fun setSound(enabled: Boolean)        = update { it.copy(soundEnabled = enabled) }
    fun setMusic(enabled: Boolean)        = update { it.copy(musicEnabled = enabled) }
    fun setVibration(enabled: Boolean)    = update { it.copy(vibrationEnabled = enabled) }
    fun setLanguage(language: Language)   = update { it.copy(language = language) }

    private fun update(transform: (AppSettings) -> AppSettings) {
        viewModelScope.launch {
            settingsRepo.save(transform(settings.value))
        }
    }
}
