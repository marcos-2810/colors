package com.colors.game

import android.app.Application
import com.colors.game.BuildConfig
import com.google.android.gms.ads.MobileAds
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ColorsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.FIREBASE_ENABLED) {
            runCatching { FirebaseApp.initializeApp(this) }
        }
        // Initialize AdMob — must happen before any ad is requested
        MobileAds.initialize(this)
    }
}
