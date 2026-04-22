package com.colors.game.presentation.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.hilt.navigation.compose.hiltViewModel
import com.colors.game.presentation.screen.*
import com.colors.game.presentation.viewmodel.SettingsViewModel
import com.colors.game.ui.LocalStrings
import com.colors.game.ui.stringsFor

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    // Collect language setting at the root so LocalStrings is available everywhere
    val settingsVm: SettingsViewModel = hiltViewModel()
    val settings by settingsVm.settings.collectAsState()
    val strings = remember(settings.language) { stringsFor(settings.language) }

    CompositionLocalProvider(LocalStrings provides strings) {
        NavHost(
            navController    = navController,
            startDestination = Screen.MainMenu.route,
            modifier         = Modifier.fillMaxSize().background(Color(0xFF121212)),
            enterTransition     = { fadeIn(tween(180, easing = LinearEasing)) },
            exitTransition      = { fadeOut(tween(120, easing = LinearEasing)) },
            popEnterTransition  = { fadeIn(tween(180, easing = LinearEasing)) },
            popExitTransition   = { fadeOut(tween(120, easing = LinearEasing)) }
        ) {
            composable(Screen.MainMenu.route) {
                MainMenuScreen(
                    onPlayNext      = { id -> navController.navigate(Screen.Game.routeFor(id)) },
                    onResume        = { id -> navController.navigate(Screen.Game.routeFor(id)) },
                    onSelectLevel   = { navController.navigate(Screen.LevelSelector.route) },
                    onSettings      = { navController.navigate(Screen.Settings.route) },
                    onExit          = { /* handled in screen */ },
                    onDailyPuzzle   = { dateKey -> navController.navigate(Screen.DailyGame.routeFor(dateKey)) },
                    onDailyCalendar = { navController.navigate(Screen.DailyCalendar.route) }
                )
            }

            composable(Screen.LevelSelector.route) {
                LevelSelectorScreen(
                    onLevelSelected = { id -> navController.navigate(Screen.Game.routeFor(id)) },
                    onBack          = { navController.popBackStack() }
                )
            }

            composable(
                route     = Screen.Game.route,
                arguments = listOf(navArgument("levelId") { type = NavType.IntType })
            ) {
                GameScreen(
                    onNavigateBack = { navController.popBackStack() },
                    onNavigateMenu = { navController.popBackStack(Screen.MainMenu.route, inclusive = false) },
                    onNavigateNext = { id ->
                        navController.navigate(Screen.Game.routeFor(id)) {
                            popUpTo(Screen.Game.route) { inclusive = true }
                        }
                    }
                )
            }

            composable(Screen.Settings.route) {
                SettingsScreen(onBack = { navController.popBackStack() })
            }

            composable(Screen.DailyCalendar.route) {
                DailyCalendarScreen(
                    onNavigateBack = { navController.popBackStack() },
                    onPlayDate     = { dateKey ->
                        navController.navigate(Screen.DailyGame.routeFor(dateKey))
                    }
                )
            }

            composable(
                route     = Screen.DailyGame.route,
                arguments = listOf(navArgument("dateKey") { type = NavType.StringType })
            ) {
                DailyGameScreen(
                    onNavigateMenu     = { navController.popBackStack(Screen.MainMenu.route, inclusive = false) },
                    onNavigateCalendar = {
                        navController.navigate(Screen.DailyCalendar.route) {
                            popUpTo(Screen.DailyGame.route) { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}
