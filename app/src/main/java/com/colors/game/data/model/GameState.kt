package com.colors.game.data.model

import kotlinx.serialization.Serializable

/** Position in the grid */
@Serializable
data class Position(val row: Int, val col: Int)

/**
 * Full in-progress game state. Serialized to DataStore for persistence.
 */
@Serializable
data class GameState(
    val levelId: Int,
    val difficulty: Difficulty,
    val rows: Int,
    val cols: Int,
    val currentCells: List<GameColor>,        // current grid (flat)
    val movesRemaining: Int,
    val timeElapsedSeconds: Int,              // elapsed since level start
    val timeLimitSeconds: Int,
    val maxMoves: Int,
    val optimalMoves: Int,
    val colorPalette: List<GameColor>,
    val selectedGroup: List<Position> = emptyList(),  // highlighted cells
    val isPaused: Boolean = false,
    val isCompleted: Boolean = false,
    val isFailed: Boolean = false
) {
    fun cellAt(row: Int, col: Int): GameColor = currentCells[row * cols + col]

    val timeRemainingSeconds: Int get() = (timeLimitSeconds - timeElapsedSeconds).coerceAtLeast(0)

    val isOver: Boolean get() = isCompleted || isFailed

    /**
     * Star rating based on performance:
     * 3 stars: solved using ≤ optimalMoves + 1 moves
     * 2 stars: solved using ≤ maxMoves * 0.7 moves
     * 1 star:  completed within the move limit
     */
    fun computeStars(): Int {
        if (!isCompleted) return 0
        val movesUsed = maxMoves - movesRemaining
        return when {
            movesUsed <= optimalMoves + 1 -> 3
            movesUsed <= (maxMoves * 0.70).toInt() -> 2
            else -> 1
        }
    }
}
