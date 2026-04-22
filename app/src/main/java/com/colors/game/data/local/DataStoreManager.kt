package com.colors.game.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.colors.game.data.model.AppSettings
import com.colors.game.data.model.DailyPuzzleRecord
import com.colors.game.data.model.GameState
import com.colors.game.data.model.LevelProgress
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "colors_prefs")

@Singleton
class DataStoreManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val json = Json { ignoreUnknownKeys = true; encodeDefaults = true }

    // ── Keys ────────────────────────────────────────────────────────────────
    private val KEY_SETTINGS      = stringPreferencesKey("settings")
    private val KEY_ACTIVE_GAME   = stringPreferencesKey("active_game")
    private val KEY_PROGRESS_MAP  = stringPreferencesKey("progress_map")
    private val KEY_DAILY_RECORDS = stringPreferencesKey("daily_records")

    // ── Settings ─────────────────────────────────────────────────────────────
    val settingsFlow: Flow<AppSettings> = context.dataStore.data.map { prefs ->
        prefs[KEY_SETTINGS]?.let { json.decodeFromString(it) } ?: AppSettings()
    }

    suspend fun saveSettings(settings: AppSettings) {
        context.dataStore.edit { it[KEY_SETTINGS] = json.encodeToString(settings) }
    }

    // ── Active game state (auto-save) ─────────────────────────────────────
    val activeGameFlow: Flow<GameState?> = context.dataStore.data.map { prefs ->
        prefs[KEY_ACTIVE_GAME]?.let { runCatching { json.decodeFromString<GameState>(it) }.getOrNull() }
    }

    suspend fun saveActiveGame(state: GameState) {
        context.dataStore.edit { it[KEY_ACTIVE_GAME] = json.encodeToString(state) }
    }

    suspend fun clearActiveGame() {
        context.dataStore.edit { it.remove(KEY_ACTIVE_GAME) }
    }

    // ── Level progress map ────────────────────────────────────────────────
    val progressMapFlow: Flow<Map<Int, LevelProgress>> = context.dataStore.data.map { prefs ->
        prefs[KEY_PROGRESS_MAP]?.let {
            runCatching {
                json.decodeFromString<List<LevelProgress>>(it).associateBy { p -> p.levelId }
            }.getOrElse { emptyMap() }
        } ?: emptyMap()
    }

    suspend fun updateLevelProgress(progress: LevelProgress) {
        context.dataStore.edit { prefs ->
            val current = prefs[KEY_PROGRESS_MAP]?.let {
                runCatching {
                    json.decodeFromString<List<LevelProgress>>(it).associateBy { p -> p.levelId }
                }.getOrElse { emptyMap() }
            }?.toMutableMap() ?: mutableMapOf()

            // Keep the best record
            val existing = current[progress.levelId]
            val merged = if (existing != null && existing.isCompleted) {
                existing.copy(
                    stars = maxOf(existing.stars, progress.stars),
                    bestMoves = minOfNotNull(existing.bestMoves, progress.bestMoves),
                    bestTimeSeconds = minOfNotNull(existing.bestTimeSeconds, progress.bestTimeSeconds)
                )
            } else {
                progress
            }
            current[progress.levelId] = merged
            prefs[KEY_PROGRESS_MAP] = json.encodeToString(current.values.toList())
        }
    }

    // ── Daily puzzle records ──────────────────────────────────────────────
    val dailyRecordsFlow: Flow<Map<String, DailyPuzzleRecord>> = context.dataStore.data.map { prefs ->
        prefs[KEY_DAILY_RECORDS]?.let {
            runCatching {
                json.decodeFromString<List<DailyPuzzleRecord>>(it).associateBy { r -> r.dateKey }
            }.getOrElse { emptyMap() }
        } ?: emptyMap()
    }

    suspend fun saveDailyRecord(record: DailyPuzzleRecord) {
        context.dataStore.edit { prefs ->
            val current = prefs[KEY_DAILY_RECORDS]?.let {
                runCatching {
                    json.decodeFromString<List<DailyPuzzleRecord>>(it).associateBy { r -> r.dateKey }
                }.getOrElse { emptyMap() }
            }?.toMutableMap() ?: mutableMapOf()

            val existing = current[record.dateKey]
            current[record.dateKey] = if (existing != null && existing.isCompleted && record.isCompleted) {
                existing.copy(
                    stars     = maxOf(existing.stars, record.stars),
                    movesUsed = minOf(existing.movesUsed, record.movesUsed),
                    timeSeconds = minOf(existing.timeSeconds, record.timeSeconds)
                )
            } else if (existing != null && existing.isCompleted) {
                existing // keep the completed record, don't overwrite with failed attempt
            } else {
                record
            }
            prefs[KEY_DAILY_RECORDS] = json.encodeToString(current.values.toList())
        }
    }

    private fun minOfNotNull(a: Int?, b: Int?): Int? = when {
        a == null -> b
        b == null -> a
        else -> minOf(a, b)
    }
}
