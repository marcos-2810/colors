package com.colors.game.data.remote;

import android.app.Activity;
import android.content.Context;
import com.colors.game.R;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import dagger.hilt.android.qualifiers.ApplicationContext;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Manages AdMob interstitial ads.
 *
 * Trigger rules (both require the user to NOT be premium):
 *  1. Every [LEVELS_PER_AD] completed levels.
 *  2. After spending [SECONDS_PER_AD] seconds in a single level.
 *
 * Setup (one-time, in AdMob console):
 *  1. Create an AdMob account and link your app.
 *  2. Create an Interstitial ad unit.
 *  3. Replace the test IDs in res/values/strings.xml with your real IDs.
 *  4. Replace the AdMob App ID in the same file.
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0019\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\r\u001a\u00020\u000eH\u0002J\u001c\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0013J\u001c\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0013J\u001e\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0013H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/colors/game/data/remote/AdManager;", "", "context", "Landroid/content/Context;", "billingManager", "Lcom/colors/game/data/remote/BillingManager;", "(Landroid/content/Context;Lcom/colors/game/data/remote/BillingManager;)V", "interstitialAd", "Lcom/google/android/gms/ads/interstitial/InterstitialAd;", "isLoading", "", "levelsSinceLastAd", "", "loadAd", "", "onLevelCompleted", "activity", "Landroid/app/Activity;", "onFinished", "Lkotlin/Function0;", "onTimeLimitReached", "tryShow", "Companion", "app_debug"})
public final class AdManager {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.colors.game.data.remote.BillingManager billingManager = null;
    public static final int LEVELS_PER_AD = 5;
    public static final int SECONDS_PER_AD = 300;
    @org.jetbrains.annotations.Nullable()
    private com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd;
    private boolean isLoading = false;
    
    /**
     * How many levels have been completed since the last ad was shown.
     */
    private int levelsSinceLastAd = 0;
    @org.jetbrains.annotations.NotNull()
    public static final com.colors.game.data.remote.AdManager.Companion Companion = null;
    
    @javax.inject.Inject()
    public AdManager(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.colors.game.data.remote.BillingManager billingManager) {
        super();
    }
    
    /**
     * Called when the player completes a level.
     * Returns true if an ad was triggered (premium users always get false).
     */
    public final boolean onLevelCompleted(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onFinished) {
        return false;
    }
    
    /**
     * Called when the player has spent too long on a level.
     * Returns true if an ad was triggered.
     */
    public final boolean onTimeLimitReached(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onFinished) {
        return false;
    }
    
    private final boolean tryShow(android.app.Activity activity, kotlin.jvm.functions.Function0<kotlin.Unit> onFinished) {
        return false;
    }
    
    private final void loadAd() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/colors/game/data/remote/AdManager$Companion;", "", "()V", "LEVELS_PER_AD", "", "SECONDS_PER_AD", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}