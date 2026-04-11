package com.colors.game.data.model

import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val daltonicMode: Boolean = false,
    val soundEnabled: Boolean = true,
    val musicEnabled: Boolean = true,
    val vibrationEnabled: Boolean = true
)
