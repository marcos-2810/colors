package com.colors.game.presentation.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.*
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
            enterTransition  = { fadeIn(tween(300)) + slideInHorizontally(tween(300)) { it / 4 } },
            exitTransition   = { fadeOut(tween(200)) + slideOutHorizontally(tween(200)) { -it / 4 } },
            popEnterTransition  = { fadeIn(tween(300)) + slideInHorizontally(tween(300)) { -it / 4 } },
            popExitTransition   = { fadeOut(tween(200)) + slideOutHorizontally(tween(200)) { it / 4 } }
        ) {
            composable(Screen.MainMenu.route) {
                MainMenuScreen(
                    onPlayNext    = { id -> navController.navigate(Screen.Game.routeFor(id)) },
                    onResume      = { id -> navController.navigate(Screen.Game.routeFor(id)) },
                    onSelectLevel = { navController.navigate(Screen.LevelSelector.route) },
                    onSettings    = { navController.navigate(Screen.Settings.route) },
                    onExit        = { /* handled in screen */ }
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
        }
    }
}
