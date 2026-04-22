package com.colors.game.presentation.navigation

sealed class Screen(val route: String) {
    object MainMenu       : Screen("main_menu")
    object LevelSelector  : Screen("level_selector")
    object Settings       : Screen("settings")
    object DailyCalendar  : Screen("daily_calendar")
    object Game           : Screen("game/{levelId}") {
        fun routeFor(levelId: Int) = "game/$levelId"
    }
    object DailyGame      : Screen("daily_game/{dateKey}") {
        fun routeFor(dateKey: String) = "daily_game/$dateKey"
    }
}
