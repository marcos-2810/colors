package com.colors.game.presentation.navigation

sealed class Screen(val route: String) {
    object MainMenu     : Screen("main_menu")
    object LevelSelector: Screen("level_selector")
    object Settings     : Screen("settings")
    object Game         : Screen("game/{levelId}") {
        fun routeFor(levelId: Int) = "game/$levelId"
    }
}
