package com.colors.game.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColors = darkColorScheme(
    primary          = Primary,
    onPrimary        = Color.White,
    primaryContainer = PrimaryDark,
    secondary        = Secondary,
    onSecondary      = Color.Black,
    background       = Surface,
    surface          = SurfaceCard,
    onBackground     = OnSurface,
    onSurface        = OnSurface,
    error            = ErrorRed,
    onError          = Color.White
)

@Composable
fun ColorsTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColors,
        typography  = ColorsTypography,
        content     = content
    )
}
