package com.colors.game.data.model

import kotlinx.serialization.Serializable

@Serializable
data class DailyPuzzleRecord(
    val dateKey: String,            // "2026-04-22"
    val isCompleted: Boolean = false,
    val stars: Int = 0,
    val movesUsed: Int = 0,
    val timeSeconds: Int = 0
)
