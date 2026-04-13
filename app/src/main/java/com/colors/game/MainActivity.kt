package com.colors.game

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.colors.game.data.remote.PlayGamesManager
import com.colors.game.presentation.navigation.NavGraph
import com.colors.game.ui.theme.ColorsTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var playGamesManager: PlayGamesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Silent GPGS sign-in check — non-blocking, no UI shown
        playGamesManager.tryAutoSignIn(this)

        setContent {
            ColorsTheme {
                NavGraph()
            }
        }
    }
}
