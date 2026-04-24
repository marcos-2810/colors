package com.colors.game.data.remote

import android.app.Activity
import android.content.Context
import com.colors.game.R
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Manages AdMob interstitial ads.
 *
 * Trigger rules (both require the user to NOT be premium):
 *   1. Every [LEVELS_PER_AD] completed levels.
 *   2. After spending [SECONDS_PER_AD] seconds in a single level.
 *
 * Setup (one-time, in AdMob console):
 *   1. Create an AdMob account and link your app.
 *   2. Create an Interstitial ad unit.
 *   3. Replace the test IDs in res/values/strings.xml with your real IDs.
 *   4. Replace the AdMob App ID in the same file.
 */
@Singleton
class AdManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val billingManager: BillingManager
) {
    companion object {
        const val LEVELS_PER_AD  = 5          // show ad after every N completions
        const val SECONDS_PER_AD = 5 * 60     // show ad after 5 minutes in one level
    }

    private var interstitialAd: InterstitialAd? = null
    private var isLoading = false

    /** How many levels have been completed since the last ad was shown. */
    private var levelsSinceLastAd = 0

    init {
        loadAd()
    }

    // ── Public API ────────────────────────────────────────────────────────────

    /**
     * Called when the player completes a level.
     * Returns true if an ad was triggered (premium users always get false).
     */
    fun onLevelCompleted(activity: Activity, onFinished: () -> Unit): Boolean {
        if (billingManager.isPremium.value) return false
        levelsSinceLastAd++
        if (levelsSinceLastAd >= LEVELS_PER_AD) {
            levelsSinceLastAd = 0
            return tryShow(activity, onFinished)
        }
        return false
    }

    /**
     * Called when the player has spent too long on a level.
     * Returns true if an ad was triggered.
     */
    fun onTimeLimitReached(activity: Activity, onFinished: () -> Unit): Boolean {
        if (billingManager.isPremium.value) return false
        return tryShow(activity, onFinished)
    }

    /**
     * Shows an ad immediately, bypassing the level-completion counter.
     * Used for special trigger points (daily puzzle start, retry limit).
     * Returns true if an ad was shown. Caller must invoke [onFinished] when false.
     */
    fun showImmediate(activity: Activity, onFinished: () -> Unit): Boolean {
        if (billingManager.isPremium.value) return false
        return tryShow(activity, onFinished)
    }

    // ── Internal ──────────────────────────────────────────────────────────────

    private fun tryShow(activity: Activity, onFinished: () -> Unit): Boolean {
        val ad = interstitialAd ?: run {
            // Not loaded yet — preload for next time, proceed without ad
            if (!isLoading) loadAd()
            return false
        }

        ad.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                interstitialAd = null
                loadAd()         // preload next ad immediately
                onFinished()
            }
            override fun onAdFailedToShowFullScreenContent(error: AdError) {
                interstitialAd = null
                loadAd()
                onFinished()     // don't block the player on ad failure
            }
        }

        ad.show(activity)
        return true
    }

    private fun loadAd() {
        if (isLoading) return
        isLoading = true
        InterstitialAd.load(
            context,
            context.getString(R.string.admob_interstitial_id),
            AdRequest.Builder().build(),
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: InterstitialAd) {
                    interstitialAd = ad
                    isLoading = false
                }
                override fun onAdFailedToLoad(error: LoadAdError) {
                    interstitialAd = null
                    isLoading = false
                }
            }
        )
    }
}
