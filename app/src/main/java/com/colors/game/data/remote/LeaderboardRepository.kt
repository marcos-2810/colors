package com.colors.game.data.remote

import com.colors.game.data.model.LeaderboardEntry
import com.colors.game.data.model.LeaderboardType
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Firestore-backed leaderboard storage.
 *
 * When google-services.json is absent (FIREBASE_ENABLED = false), [db] will be null
 * and every public function is a silent no-op / returns empty data.
 *
 * Firestore collection: "scores"
 * Document ID:  "{levelId}_{type}_{playerId}"  ← one doc per player per level per metric
 *
 * Fields: levelId, type, playerId, playerName, score, updatedAt
 *
 * Required composite index (Firestore will prompt to create it on first query):
 *   Collection: scores
 *   Fields:     levelId ASC, type ASC, score ASC
 *
 * Firestore security rules (deploy via Firebase console):
 * ──────────────────────────────────────────────────────
 * rules_version = '2';
 * service cloud.firestore {
 *   match /databases/{database}/documents {
 *     match /scores/{scoreId} {
 *       allow read: if true;
 *       allow write: if scoreId.matches(request.resource.data.playerId + '.*');
 *     }
 *   }
 * }
 */
@Singleton
class LeaderboardRepository @Inject constructor(
    private val playGamesManager: PlayGamesManager
) {
    /**
     * Lazily obtain the Firestore instance.
     * Returns null if Firebase was never initialized (no google-services.json).
     */
    private val db: FirebaseFirestore? by lazy {
        runCatching { FirebaseFirestore.getInstance() }.getOrNull()
    }

    /** Whether the leaderboard backend is available at runtime. */
    val isAvailable: Boolean get() = db != null

    companion object {
        private const val TOP_ENTRIES = 50L
        private const val COLLECTION  = "scores"
    }

    // ── Read ──────────────────────────────────────────────────────────────────

    /**
     * Returns the top [TOP_ENTRIES] players for [levelId] ranked by [type] (lower = better).
     * Returns an empty list when Firebase is unavailable or on network error — never throws.
     */
    suspend fun getTopScores(
        levelId: Int,
        type: LeaderboardType
    ): List<LeaderboardEntry> {
        val col = db?.collection(COLLECTION) ?: return emptyList()

        return runCatching {
            val snapshot = col
                .whereEqualTo("levelId", levelId)
                .whereEqualTo("type", type.key)
                .orderBy("score", Query.Direction.ASCENDING)
                .limit(TOP_ENTRIES)
                .get()
                .await()

            val currentPlayerId = playGamesManager.player.value?.id

            snapshot.documents.mapIndexed { index, doc ->
                val pid = doc.getString("playerId") ?: ""
                LeaderboardEntry(
                    rank            = index + 1,
                    playerId        = pid,
                    playerName      = doc.getString("playerName") ?: "Player",
                    score           = doc.getLong("score")?.toInt() ?: 0,
                    isCurrentPlayer = pid == currentPlayerId
                )
            }
        }.getOrDefault(emptyList())
    }

    // ── Write ─────────────────────────────────────────────────────────────────

    /**
     * Submits [score] for [levelId]/[type] only if the player is signed in and
     * the score improves on their personal best. Silent no-op when Firebase is
     * unavailable, the player is not signed in, or on network error.
     */
    suspend fun submitScore(levelId: Int, type: LeaderboardType, score: Int) {
        val col    = db?.collection(COLLECTION) ?: return
        val player = playGamesManager.player.value ?: return

        runCatching {
            val docId  = "${levelId}_${type.key}_${player.id}"
            val docRef = col.document(docId)

            val existing = docRef.get().await()
            val current  = existing.getLong("score")?.toInt()

            // Only persist if it's a new personal best (lower score = better)
            if (current == null || score < current) {
                docRef.set(
                    mapOf(
                        "levelId"    to levelId,
                        "type"       to type.key,
                        "playerId"   to player.id,
                        "playerName" to player.name,
                        "score"      to score,
                        "updatedAt"  to com.google.firebase.Timestamp.now()
                    )
                ).await()
            }
        }
        // Silently swallow network errors — scores are best-effort
    }
}
