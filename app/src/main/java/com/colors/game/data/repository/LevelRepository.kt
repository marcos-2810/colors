package com.colors.game.data.repository

import com.colors.game.data.model.Difficulty
import com.colors.game.data.model.Level
import com.colors.game.domain.LevelGenerator
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Provides levels on demand using the procedural generator.
 * Levels are cached in memory after first generation so subsequent requests
 * are instantaneous.
 */
@Singleton
class LevelRepository @Inject constructor(
    private val generator: LevelGenerator
) {
    // In-memory cache (id → Level)
    private val cache = HashMap<Int, Level>(LevelGenerator.TOTAL_LEVELS)

    fun getLevel(id: Int): Level = cache.getOrPut(id) { generator.generateLevel(id) }

    fun getLevelsForDifficulty(difficulty: Difficulty): List<Level> {
        val range = when (difficulty) {
            Difficulty.EASY   -> 1..100
            Difficulty.MEDIUM -> 101..200
            Difficulty.HARD   -> 201..500
        }
        return range.map { getLevel(it) }
    }

    fun getTotalLevels(): Int = LevelGenerator.TOTAL_LEVELS

    fun difficultyFor(id: Int): Difficulty = generator.difficultyForId(id)

    /** Pre-warm the cache for a range of levels in the background */
    fun preWarm(ids: IntRange) {
        ids.forEach { id -> if (id in 1..LevelGenerator.TOTAL_LEVELS) getLevel(id) }
    }
}
