package com.colors.game.data.model

/** A single row in a leaderboard ranking. */
data class LeaderboardEntry(
    val rank: Int,
    val playerId: String,
    val playerName: String,
    /** Raw score: moves used (lower = better) or seconds elapsed (lower = better). */
    val score: Int,
    /** True when this entry belongs to the currently signed-in player. */
    val isCurrentPlayer: Boolean = false
)

enum class LeaderboardType(val key: String) {
    MOVES("moves"),
    TIME("time")
}
