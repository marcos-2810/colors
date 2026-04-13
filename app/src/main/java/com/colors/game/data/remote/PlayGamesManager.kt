package com.colors.game.data.remote

import android.app.Activity
import android.content.Context
import com.google.android.gms.games.PlayGames
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Manages Google Play Games Services v2 sign-in.
 *
 * Prerequisites (one-time setup by the developer):
 * ─────────────────────────────────────────────────
 * 1. Enable Play Games Services in Google Play Console.
 * 2. Create an OAuth 2.0 client ID for Android (SHA-1 + package name).
 * 3. In Firebase console, add the Google provider under Authentication.
 * 4. Update res/values/strings.xml with your game_services_project_id.
 *
 * Usage:
 *   Call [tryAutoSignIn] from MainActivity.onCreate (non-blocking).
 *   Call [signIn] when the user explicitly taps a sign-in button.
 */
@Singleton
class PlayGamesManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    data class PlayerInfo(
        val id: String,
        val name: String
    )

    private val _player = MutableStateFlow<PlayerInfo?>(null)
    val player: StateFlow<PlayerInfo?> = _player.asStateFlow()

    val isSignedIn: Boolean get() = _player.value != null

    // ── Public API ────────────────────────────────────────────────────────────

    /**
     * Silently checks if the user is already authenticated.
     * Call from [Activity.onCreate] — never blocks the UI.
     */
    fun tryAutoSignIn(activity: Activity) {
        PlayGames.getGamesSignInClient(activity)
            .isAuthenticated
            .addOnSuccessListener { result ->
                if (result.isAuthenticated) fetchPlayer(activity)
            }
    }

    /**
     * Prompts the user to sign in with their Google Play Games account.
     * [onResult] is called on the main thread with true on success.
     */
    fun signIn(activity: Activity, onResult: (Boolean) -> Unit) {
        PlayGames.getGamesSignInClient(activity)
            .signIn()
            .addOnSuccessListener {
                fetchPlayer(activity)
                onResult(true)
            }
            .addOnFailureListener { onResult(false) }
    }

    fun signOut() {
        _player.value = null
    }

    // ── Internal ──────────────────────────────────────────────────────────────

    private fun fetchPlayer(activity: Activity) {
        PlayGames.getPlayersClient(activity)
            .currentPlayer
            .addOnSuccessListener { player ->
                _player.value = PlayerInfo(
                    id   = player.playerId,
                    name = player.displayName
                )
            }
    }
}
