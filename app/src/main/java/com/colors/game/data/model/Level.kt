package com.colors.game.data.model

import kotlinx.serialization.Serializable

/**
 * Immutable definition of a level. Stored/cached once generated.
 *
 * Grid is stored as a flat list, row-major order:
 *   index = row * cols + col
 */
@Serializable
data class Level(
    val id: Int,
    val difficulty: Difficulty,
    val rows: Int,
    val cols: Int,
    val initialCells: List<GameColor>,  // size = rows * cols
    val maxMoves: Int,
    val timeLimitSeconds: Int,
    val colorPalette: List<GameColor>,
    val optimalMoves: Int               // greedy-solver baseline for star scoring
) {
    fun cellAt(row: Int, col: Int): GameColor = initialCells[row * cols + col]
}
