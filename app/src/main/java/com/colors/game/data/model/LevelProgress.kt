package com.colors.game.data.model

import kotlinx.serialization.Serializable

/**
 * Persisted progress for a single level.
 * Stars: 3 = excellent, 2 = good, 1 = completed, 0 = not completed.
 */
@Serializable
data class LevelProgress(
    val levelId: Int,
    val isCompleted: Boolean = false,
    val stars: Int = 0,          // 0-3
    val bestMoves: Int? = null,  // fewest moves used
    val bestTimeSeconds: Int? = null
)
