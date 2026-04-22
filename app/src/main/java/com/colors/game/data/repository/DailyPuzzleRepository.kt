package com.colors.game.data.repository

import com.colors.game.data.local.DataStoreManager
import com.colors.game.data.model.DailyPuzzleRecord
import com.colors.game.data.model.Level
import com.colors.game.domain.LevelGenerator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DailyPuzzleRepository @Inject constructor(
    private val dataStore: DataStoreManager,
    private val levelGenerator: LevelGenerator
) {
    companion object {
        /** The first day daily puzzles are available. */
        val START_DATE: LocalDate = LocalDate.of(2026, 4, 22)
        private val FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE
    }

    // ── Date helpers ──────────────────────────────────────────────────────

    fun todayKey(): String = LocalDate.now().format(FORMATTER)

    /** All date keys from START_DATE up to today, most recent first. */
    fun availableDateKeys(): List<String> {
        val today = LocalDate.now()
        val keys  = mutableListOf<String>()
        var cur   = if (today.isBefore(START_DATE)) START_DATE else today
        val end   = if (today.isBefore(START_DATE)) today else START_DATE
        while (!cur.isBefore(end)) {
            keys.add(cur.format(FORMATTER))
            cur = cur.minusDays(1)
        }
        return keys
    }

    // ── Level generation ──────────────────────────────────────────────────

    fun generateLevelForDate(dateKey: String): Level {
        val epoch = LocalDate.parse(dateKey, FORMATTER).toEpochDay()
        return levelGenerator.generateDaily(epoch)
    }

    // ── Persistence ───────────────────────────────────────────────────────

    val recordsFlow: Flow<Map<String, DailyPuzzleRecord>> = dataStore.dailyRecordsFlow

    fun hasTodayBeenPlayedFlow(): Flow<Boolean> =
        dataStore.dailyRecordsFlow.map { it.containsKey(todayKey()) }

    suspend fun saveRecord(record: DailyPuzzleRecord) =
        dataStore.saveDailyRecord(record)
}
