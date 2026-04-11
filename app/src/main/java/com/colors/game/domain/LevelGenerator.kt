package com.colors.game.domain

import com.colors.game.data.model.Difficulty
import com.colors.game.data.model.GameColor
import com.colors.game.data.model.Level
import kotlin.random.Random

/**
 * Procedural level generator.
 *
 * Design principles:
 * ─────────────────
 * 1. Deterministic: same (id, seed) always produces the same level.
 * 2. Guaranteed solvable: greedy solver validates every generated grid.
 * 3. Progressive difficulty: grid size and color count scale with id.
 * 4. Balanced maxMoves: optimal + difficulty-dependent slack keeps levels
 *    beatable but challenging.
 *
 * Distribution (500 total levels):
 *   IDs   1-100 → EASY   (4 colors,  5×5 → 7×7)
 *   IDs 101-200 → MEDIUM (6 colors,  7×7 → 9×9)
 *   IDs 201-500 → HARD   (8 colors,  9×9 → 12×12)
 */
class LevelGenerator(private val solver: GreedySolver = GreedySolver()) {

    companion object {
        const val TOTAL_LEVELS = 500
        private const val MAX_GEN_ATTEMPTS = 30
    }

    fun generateLevel(id: Int): Level {
        require(id in 1..TOTAL_LEVELS) { "Level id must be 1-$TOTAL_LEVELS" }
        val difficulty = difficultyForId(id)

        // Try seeds until a valid level is produced
        var seed = id.toLong()
        var attempt = 0
        while (attempt < MAX_GEN_ATTEMPTS) {
            val level = tryGenerate(id, difficulty, seed)
            if (level != null) return level
            seed += TOTAL_LEVELS
            attempt++
        }

        // Fallback: very generous maxMoves so it's always winnable
        return tryGenerate(id, difficulty, seed, forcedSlack = 20)!!
    }

    // -----------------------------------------------------------------------

    private fun tryGenerate(
        id: Int,
        difficulty: Difficulty,
        seed: Long,
        forcedSlack: Int? = null
    ): Level? {
        val rng = Random(seed)
        val (rows, cols) = dimensionsFor(id, difficulty)
        val palette = difficulty.colorPalette

        // Generate random grid; use weighted random to encourage clusters
        val cells = buildClusteredGrid(rows, cols, palette, rng)

        // Validate with greedy solver
        val optimalMoves = solver.solve(cells, rows, cols, palette)
        val totalCells = rows * cols

        // Reject grids that are trivially easy (< 3 moves) or impossibly hard
        val minAcceptableMoves = when (difficulty) {
            Difficulty.EASY   -> 3
            Difficulty.MEDIUM -> 5
            Difficulty.HARD   -> 8
        }
        val maxAcceptableMoves = totalCells  // upper sanity bound
        if (optimalMoves < minAcceptableMoves || optimalMoves > maxAcceptableMoves) {
            return null
        }

        val slack = forcedSlack ?: when (difficulty) {
            Difficulty.EASY   -> rng.nextInt(4, 8)   // 4-7 extra moves
            Difficulty.MEDIUM -> rng.nextInt(3, 6)   // 3-5 extra moves
            Difficulty.HARD   -> rng.nextInt(1, 4)   // 1-3 extra moves
        }

        return Level(
            id = id,
            difficulty = difficulty,
            rows = rows,
            cols = cols,
            initialCells = cells,
            maxMoves = optimalMoves + slack,
            timeLimitSeconds = difficulty.timeLimitSeconds,
            colorPalette = palette,
            optimalMoves = optimalMoves
        )
    }

    /**
     * Build a grid with clusters of the same color.
     * Uses a "painter" approach: place color seeds, then grow them with BFS.
     * This creates visually interesting islands instead of pure random noise.
     */
    private fun buildClusteredGrid(
        rows: Int,
        cols: Int,
        palette: List<GameColor>,
        rng: Random
    ): List<GameColor> {
        val cells = Array(rows * cols) { palette[rng.nextInt(palette.size)] }

        // Apply local smoothing passes to form clusters
        val smoothingPasses = when (palette.size) {
            4 -> 3   // Easy: more smoothing → larger clusters → easier
            6 -> 2   // Medium
            else -> 1 // Hard: less smoothing → more fragmented → harder
        }

        repeat(smoothingPasses) {
            for (row in 0 until rows) {
                for (col in 0 until cols) {
                    val neighbors = getNeighborColors(cells, rows, cols, row, col)
                    if (neighbors.isNotEmpty()) {
                        // With 50% chance, adopt the most common neighbor color
                        if (rng.nextFloat() < 0.5f) {
                            cells[row * cols + col] = neighbors.groupBy { it }
                                .maxBy { it.value.size }.key
                        }
                    }
                }
            }
        }

        return cells.toList()
    }

    private fun getNeighborColors(
        cells: Array<GameColor>,
        rows: Int,
        cols: Int,
        row: Int,
        col: Int
    ): List<GameColor> {
        val dirs = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)
        return dirs.mapNotNull { (dr, dc) ->
            val nr = row + dr; val nc = col + dc
            if (nr in 0 until rows && nc in 0 until cols) cells[nr * cols + nc] else null
        }
    }

    // -----------------------------------------------------------------------
    // Level ID → Difficulty mapping
    // -----------------------------------------------------------------------
    fun difficultyForId(id: Int): Difficulty = when (id) {
        in 1..100   -> Difficulty.EASY
        in 101..200 -> Difficulty.MEDIUM
        else        -> Difficulty.HARD
    }

    /**
     * Grid dimensions scale progressively within each difficulty tier.
     * Sizes chosen to keep runtime low and gameplay engaging on mobile.
     */
    private fun dimensionsFor(id: Int, difficulty: Difficulty): Pair<Int, Int> {
        return when (difficulty) {
            Difficulty.EASY -> {
                // IDs 1-100: 5×5 → 6×6 → 7×7
                val p = (id - 1) / 99f
                when {
                    p < 0.33f -> 5 to 5
                    p < 0.67f -> 6 to 6
                    else       -> 7 to 7
                }
            }
            Difficulty.MEDIUM -> {
                // IDs 101-200: 7×7 → 8×8 → 9×9
                val p = (id - 101) / 99f
                when {
                    p < 0.33f -> 7 to 7
                    p < 0.67f -> 8 to 8
                    else       -> 9 to 9
                }
            }
            Difficulty.HARD -> {
                // IDs 201-500: 9×9 → 10×10 → 11×11 → 12×12
                val p = (id - 201) / 299f
                when {
                    p < 0.25f -> 9  to 9
                    p < 0.50f -> 10 to 10
                    p < 0.75f -> 11 to 11
                    else       -> 12 to 12
                }
            }
        }
    }
}
