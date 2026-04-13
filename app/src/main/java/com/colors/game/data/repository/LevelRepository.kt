package com.colors.game.data.repository

import android.content.Context
import com.colors.game.data.model.Difficulty
import com.colors.game.data.model.Level
import com.colors.game.domain.LevelGenerator
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import java.io.File
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Provides levels from a pre-generated JSON cache stored in internal storage.
 *
 * First launch: generates all 500 levels in a background thread and saves
 *   them to filesDir/levels_v1.json (one-time cost, ~1-3 seconds).
 * Every other launch: reads the JSON file — all 500 levels in memory in
 *   milliseconds, zero runtime generation.
 *
 * Observe [isReady] to know when levels are available.
 */
@Singleton
class LevelRepository @Inject constructor(
    private val generator: LevelGenerator,
    @ApplicationContext private val context: Context
) {
    companion object {
        // Bump the version suffix to force a regeneration (e.g. after changing LevelGenerator)
        private const val CACHE_FILE = "levels_v1.json"
    }

    private val json      = Json { ignoreUnknownKeys = true }
    private val cacheFile = File(context.filesDir, CACHE_FILE)
    private val cache     = ConcurrentHashMap<Int, Level>(LevelGenerator.TOTAL_LEVELS)
    private val scope     = CoroutineScope(Dispatchers.IO + SupervisorJob())

    private val _isReady = MutableStateFlow(false)
    val isReady: StateFlow<Boolean> = _isReady.asStateFlow()

    init {
        scope.launch {
            runCatching {
                if (cacheFile.exists()) loadFromFile() else generateAndSave()
            }.onFailure {
                // Corrupted / incompatible file — nuke it and regenerate
                cacheFile.delete()
                runCatching { generateAndSave() }
            }
            _isReady.value = true
        }
    }

    // ── Public API ────────────────────────────────────────────────────────────

    /**
     * Returns a level synchronously. If the background pre-generation has
     * finished (isReady == true) this is an O(1) map lookup. If called before
     * that (edge case on very first launch), it falls back to on-demand
     * generation for just this one level.
     */
    fun getLevel(id: Int): Level =
        cache.computeIfAbsent(id) { generator.generateLevel(it) }

    fun getLevelsForDifficulty(difficulty: Difficulty): List<Level> =
        rangeFor(difficulty).map { getLevel(it) }

    fun getTotalLevels(): Int = LevelGenerator.TOTAL_LEVELS

    fun difficultyFor(id: Int): Difficulty = generator.difficultyForId(id)

    // ── File I/O (runs on Dispatchers.IO via scope.launch in init) ────────────

    private fun loadFromFile() {
        val levels = json.decodeFromString(
            ListSerializer(Level.serializer()),
            cacheFile.readText()
        )
        // Validate the file matches the expected number of levels
        if (levels.size != LevelGenerator.TOTAL_LEVELS) {
            cacheFile.delete()
            generateAndSave()
            return
        }
        levels.forEach { cache[it.id] = it }
    }

    private fun generateAndSave() {
        val levels = (1..LevelGenerator.TOTAL_LEVELS).map { id ->
            generator.generateLevel(id).also { cache[id] = it }
        }
        cacheFile.writeText(
            json.encodeToString(ListSerializer(Level.serializer()), levels)
        )
    }

    private fun rangeFor(difficulty: Difficulty) = when (difficulty) {
        Difficulty.EASY   -> 1..100
        Difficulty.MEDIUM -> 101..200
        Difficulty.HARD   -> 201..500
    }
}
