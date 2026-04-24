package com.colors.game.presentation.viewmodel

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.colors.game.data.remote.AdManager
import com.colors.game.data.remote.BillingManager
import com.colors.game.data.repository.DailyPuzzleRepository
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
    val activeGameCoverage: Float? = null,
    /** True when the user has purchased the premium upgrade. */
    val isPremium: Boolean = false,
    /** True if today's daily puzzle has already been played. */
    val dailyPlayedToday: Boolean = false,
    /** Today's date key "yyyy-MM-dd" for navigation. */
    val todayKey: String = ""
)

@HiltViewModel
class MainMenuViewModel @Inject constructor(
    private val gameStateRepo: GameStateRepository,
    private val settingsRepo: SettingsRepository,
    private val billingManager: BillingManager,
    private val dailyRepo: DailyPuzzleRepository,
    private val adManager: AdManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainMenuUiState())
    val uiState: StateFlow<MainMenuUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val todayKey = dailyRepo.todayKey()
            combine(
                gameStateRepo.progressMapFlow,
                gameStateRepo.activeGameFlow,
                settingsRepo.settingsFlow,
                billingManager.isPremium,
                dailyRepo.hasTodayBeenPlayedFlow()
            ) { args ->
                @Suppress("UNCHECKED_CAST")
                val progressMap       = args[0] as Map<Int, com.colors.game.data.model.LevelProgress>
                val activeGame        = args[1] as? com.colors.game.data.model.GameState
                val settings          = args[2] as com.colors.game.data.model.AppSettings
                val isPremiumBilling  = args[3] as Boolean
                val dailyPlayedToday  = args[4] as Boolean

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
                    nextLevelId        = nextId,
                    hasNeverPlayed     = !hasEverPlayed,
                    resumableLevelId   = activeGame?.takeIf { !it.isOver }?.levelId,
                    totalCompleted     = completed,
                    tutorialCompleted  = settings.tutorialCompleted,
                    activeGameCoverage = coverage,
                    isPremium          = isPremiumBilling || settings.isPremiumUnlocked,
                    dailyPlayedToday   = dailyPlayedToday,
                    todayKey           = todayKey
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

    /**
     * Shows an interstitial ad before entering the daily puzzle for the first time today.
     * [onReady] is called after the ad is dismissed (or immediately if no ad is available).
     */
    fun requestDailyStartAd(activity: Activity, onReady: () -> Unit) {
        val shown = adManager.showImmediate(activity, onReady)
        if (!shown) onReady()
    }

    /** Abre el flujo de compra de Google Play para el upgrade Premium. */
    fun purchasePremium(activity: Activity) {
        billingManager.launchPurchaseFlow(activity)
    }

    /**
     * Validates [code] against the known promo codes.
     * Returns true if valid (and persists the unlock), false otherwise.
     */
    suspend fun redeemCode(code: String): Boolean {
        val valid = code.trim().uppercase() == "EASY_PREMIUM_GG"
        if (valid) {
            val current = settingsRepo.settingsFlow.first()
            settingsRepo.save(current.copy(isPremiumUnlocked = true))
        }
        return valid
    }
}
