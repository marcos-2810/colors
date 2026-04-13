package com.colors.game.data.model

import kotlinx.serialization.Serializable

@Serializable
enum class Difficulty(
    val displayName: String,
    val colorPalette: List<GameColor>,
    val timeLimitSeconds: Int
) {
    EASY(
        displayName = "Fácil",
        colorPalette = listOf(
            GameColor.RED, GameColor.GREEN, GameColor.BLUE, GameColor.YELLOW
        ),
        timeLimitSeconds = 300
    ),
    MEDIUM(
        displayName = "Medio",
        colorPalette = listOf(
            GameColor.RED, GameColor.GREEN, GameColor.BLUE,
            GameColor.YELLOW, GameColor.BROWN, GameColor.PURPLE
        ),
        timeLimitSeconds = 600
    ),
    HARD(
        displayName = "Difícil",
        colorPalette = listOf(
            GameColor.RED, GameColor.GREEN, GameColor.BLUE, GameColor.YELLOW,
            GameColor.BROWN, GameColor.PURPLE, GameColor.ORANGE, GameColor.PINK
        ),
        timeLimitSeconds = 900
    )
}
