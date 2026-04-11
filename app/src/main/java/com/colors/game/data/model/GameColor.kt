package com.colors.game.data.model

import androidx.compose.ui.graphics.Color
import kotlinx.serialization.Serializable

/**
 * All playable colors in the game.
 * Each color has a normal variant and a colorblind-safe (daltonism) variant.
 * Difficulty controls which subset of colors is available per level.
 */
@Serializable
enum class GameColor {
    RED,
    GREEN,
    BLUE,
    YELLOW,
    BROWN,
    PURPLE,
    ORANGE,
    BLACK;

    fun toComposeColor(daltonicMode: Boolean = false): Color = when (this) {
        RED    -> if (daltonicMode) Color(0xFFCC3311) else Color(0xFFE53935)
        GREEN  -> if (daltonicMode) Color(0xFF009988) else Color(0xFF43A047)
        BLUE   -> if (daltonicMode) Color(0xFF0077BB) else Color(0xFF1E88E5)
        YELLOW -> if (daltonicMode) Color(0xFFEECC66) else Color(0xFFFFD600)
        BROWN  -> if (daltonicMode) Color(0xFF994455) else Color(0xFF6D4C41)
        PURPLE -> if (daltonicMode) Color(0xFFAA3377) else Color(0xFF8E24AA)
        ORANGE -> if (daltonicMode) Color(0xFFEE7733) else Color(0xFFF57C00)
        BLACK  -> if (daltonicMode) Color(0xFF222255) else Color(0xFF212121)
    }

    /** Lighter tint used for selected cell highlight */
    fun toHighlightColor(daltonicMode: Boolean = false): Color =
        toComposeColor(daltonicMode).copy(alpha = 0.35f)
}
