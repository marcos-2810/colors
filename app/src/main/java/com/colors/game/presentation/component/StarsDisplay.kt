package com.colors.game.presentation.component

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import com.colors.game.ui.theme.StarColor
import kotlinx.coroutines.delay

@Composable
fun StarsDisplay(
    stars: Int,
    maxStars: Int = 3,
    animated: Boolean = false,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 1..maxStars) {
            AnimatedStar(
                filled     = i <= stars,
                delayMs    = if (animated) (i - 1) * 200 else 0,
                animated   = animated
            )
        }
    }
}

@Composable
private fun AnimatedStar(
    filled: Boolean,
    delayMs: Int,
    animated: Boolean
) {
    var visible by remember { mutableStateOf(!animated) }

    LaunchedEffect(animated) {
        if (animated) {
            delay(delayMs.toLong())
            visible = true
        }
    }

    val scale by animateFloatAsState(
        targetValue = if (visible && filled) 1f else if (!filled) 1f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness    = Spring.StiffnessMediumLow
        ),
        label = "starScale"
    )

    Icon(
        imageVector        = if (filled) Icons.Filled.Star else Icons.Outlined.StarOutline,
        contentDescription = null,
        tint               = StarColor,
        modifier           = Modifier.size(32.dp).scale(scale)
    )
}
