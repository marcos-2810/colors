package com.colors.game.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.colors.game.data.model.LeaderboardEntry
import com.colors.game.data.model.LeaderboardType
import com.colors.game.data.remote.LeaderboardRepository
import com.colors.game.data.remote.PlayGamesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LeaderboardUiState(
    val movesEntries: List<LeaderboardEntry> = emptyList(),
    val timeEntries:  List<LeaderboardEntry> = emptyList(),
    val isLoadingMoves: Boolean = false,
    val isLoadingTime:  Boolean = false,
    val isSignedIn: Boolean = false,
    val playerName: String? = null,
    /** Index of the currently visible tab: 0 = Moves, 1 = Time */
    val selectedTab: Int = 0,
    val currentLevelId: Int = -1
)

@HiltViewModel
class LeaderboardViewModel @Inject constructor(
    private val leaderboardRepo: LeaderboardRepository,
    private val playGamesManager: PlayGamesManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(LeaderboardUiState())
    val uiState: StateFlow<LeaderboardUiState> = _uiState.asStateFlow()

    init {
        // Keep sign-in status in sync with PlayGamesManager
        viewModelScope.launch {
            playGamesManager.player.collect { player ->
                _uiState.update {
                    it.copy(
                        isSignedIn = player != null,
                        playerName = player?.name
                    )
                }
                // Refresh rankings with updated "isCurrentPlayer" flags
                val levelId = _uiState.value.currentLevelId
                if (levelId != -1) loadAll(levelId)
            }
        }
    }

    // ── Public ────────────────────────────────────────────────────────────────

    /** Load (or reload) leaderboards for [levelId]. Idempotent if same level. */
    fun loadForLevel(levelId: Int) {
        if (_uiState.value.currentLevelId == levelId) return
        _uiState.update { it.copy(currentLevelId = levelId, selectedTab = 0) }
        loadAll(levelId)
    }

    /** Force-refresh the current level's leaderboards. */
    fun refresh() {
        val levelId = _uiState.value.currentLevelId
        if (levelId != -1) loadAll(levelId)
    }

    fun selectTab(index: Int) = _uiState.update { it.copy(selectedTab = index) }

    // ── Internal ──────────────────────────────────────────────────────────────

    private fun loadAll(levelId: Int) {
        loadType(levelId, LeaderboardType.MOVES)
        loadType(levelId, LeaderboardType.TIME)
    }

    private fun loadType(levelId: Int, type: LeaderboardType) {
        viewModelScope.launch {
            val isMovesTab = type == LeaderboardType.MOVES
            _uiState.update {
                if (isMovesTab) it.copy(isLoadingMoves = true)
                else            it.copy(isLoadingTime  = true)
            }

            val entries = leaderboardRepo.getTopScores(levelId, type)

            _uiState.update {
                if (isMovesTab) it.copy(movesEntries = entries, isLoadingMoves = false)
                else            it.copy(timeEntries  = entries, isLoadingTime  = false)
            }
        }
    }
}
