package com.colors.game.presentation.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.colors.game.data.model.GameColor

/**
 * Horizontal row of color buttons.
 *
 * The currently-active color (same as selected group) is subtly dimmed
 * to indicate it's not a valid selection (tapping it is a no-op).
 *
 * Layout adapts to palette size — up to 8 colors fits comfortably on
 * a standard mobile screen in a single row.
 */
@Composable
fun ColorPicker(
    palette: List<GameColor>,
    activeColor: GameColor?,        // currently selected group's color
    daltonicMode: Boolean,
    onColorSelected: (GameColor) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        palette.forEach { color ->
            val isActive = color == activeColor
            ColorButton(
                color        = color,
                isActive     = isActive,
                daltonicMode = daltonicMode,
                onClick      = { if (!isActive) onColorSelected(color) }
            )
        }
    }
}

@Composable
private fun ColorButton(
    color: GameColor,
    isActive: Boolean,
    daltonicMode: Boolean,
    onClick: () -> Unit
) {
    val composeColor = color.toComposeColor(daltonicMode)

    val scale by animateFloatAsState(
        targetValue = if (isActive) 0.80f else 1.0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness    = Spring.StiffnessMedium
        ),
        label = "colorBtnScale"
    )

    val borderColor by animateColorAsState(
        targetValue = if (isActive) Color.White.copy(alpha = 0.3f) else Color.White.copy(alpha = 0.7f),
        label = "colorBtnBorder"
    )

    Box(
        modifier = Modifier
            .size(48.dp)
            .scale(scale)
            .clip(CircleShape)
            .background(composeColor)
            .border(2.dp, borderColor, CircleShape)
            .clickable(onClick = onClick)
    )
}
