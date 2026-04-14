package com.colors.game.data.model

import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val daltonicMode: Boolean = false,
    val soundEnabled: Boolean = true,
    val musicEnabled: Boolean = true,
    val vibrationEnabled: Boolean = true,
    val tutorialCompleted: Boolean = false,
    val language: Language = Language.EN,
    /** True when premium was unlocked via a promo code (persisted locally). */
    val isPremiumUnlocked: Boolean = false
)
