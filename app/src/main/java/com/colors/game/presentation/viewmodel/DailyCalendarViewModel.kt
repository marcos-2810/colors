package com.colors.game.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.colors.game.data.model.DailyPuzzleRecord
import com.colors.game.data.model.Difficulty
import com.colors.game.data.repository.DailyPuzzleRepository
import com.colors.game.domain.LevelGenerator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

data class DailyCalendarEntry(
    val dateKey: String,
    val isToday: Boolean,
    val difficulty: Difficulty,
    val record: DailyPuzzleRecord?   // null = not played yet
)

data class DailyCalendarUiState(
    val entries: List<DailyCalendarEntry> = emptyList(),
    val todayKey: String = "",
    val isLoading: Boolean = true
)

@HiltViewModel
class DailyCalendarViewModel @Inject constructor(
    private val dailyRepo: DailyPuzzleRepository,
    private val levelGenerator: LevelGenerator
) : ViewModel() {

    private val _uiState = MutableStateFlow(DailyCalendarUiState())
    val uiState: StateFlow<DailyCalendarUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val todayKey    = dailyRepo.todayKey()
            val dateKeys    = withContext(Dispatchers.Default) { dailyRepo.availableDateKeys() }

            // Pre-compute difficulty for each date (deterministic, fast)
            val difficulties = withContext(Dispatchers.Default) {
                dateKeys.associateWith { key ->
                    val epoch = LocalDate.parse(key, DateTimeFormatter.ISO_LOCAL_DATE).toEpochDay()
                    val diff  = levelGenerator.generateDaily(epoch).difficulty
                    diff
                }
            }

            dailyRepo.recordsFlow.collect { records ->
                val entries = dateKeys.map { key ->
                    DailyCalendarEntry(
                        dateKey    = key,
                        isToday    = key == todayKey,
                        difficulty = difficulties[key] ?: Difficulty.MEDIUM,
                        record     = records[key]
                    )
                }
                _uiState.update {
                    it.copy(entries = entries, todayKey = todayKey, isLoading = false)
                }
            }
        }
    }
}
