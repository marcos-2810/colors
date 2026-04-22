package com.colors.game.presentation.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.colors.game.data.model.GameColor
import com.colors.game.data.model.Position

/**
 * Renders the NxM game grid.
 *
 * El grupo activo siempre parte de (0,0) — las celdas NO son tapeables.
 * El resaltado del grupo activo sigue existiendo con animación de pulso.
 */
@Composable
fun GameGrid(
    rows: Int,
    cols: Int,
    cells: List<GameColor>,
    selectedGroup: Set<Position>,
    animatingCells: Map<Position, Int>,
    daltonicMode: Boolean,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(3.dp)) {
        for (row in 0 until rows) {
            Row(horizontalArrangement = Arrangement.spacedBy(3.dp)) {
                for (col in 0 until cols) {
                    val pos = Position(row, col)
                    val color = cells[row * cols + col]
                    val isSelected = pos in selectedGroup
                    val waveDistance = animatingCells[pos]

                    key(row, col) {
                        GridCell(
                            color        = color,
                            isSelected   = isSelected,
                            waveDistance = waveDistance,
                            daltonicMode = daltonicMode,
                            modifier     = Modifier.weight(1f).aspectRatio(1f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun GridCell(
    color: GameColor,
    isSelected: Boolean,
    waveDistance: Int?,
    daltonicMode: Boolean,
    modifier: Modifier = Modifier
) {
    val animatedColor by animateColorAsState(
        targetValue   = color.toComposeColor(daltonicMode),
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness    = Spring.StiffnessMedium
        ),
        label = "cellColor"
    )

    val waveDelay = (waveDistance ?: 0) * 60
    val waveScale by animateFloatAsState(
        targetValue   = if (waveDistance != null) 1f else 1f,
        animationSpec = if (waveDistance != null) {
            tween(durationMillis = 300, delayMillis = waveDelay, easing = FastOutSlowInEasing)
        } else snap(),
        label = "waveScale"
    )

    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val pulseBorderAlpha by infiniteTransition.animateFloat(
        initialValue  = 0.25f,
        targetValue   = 0.60f,
        animationSpec = infiniteRepeatable(
            animation  = tween(1200, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulseBorder"
    )

    val cellShape = RoundedCornerShape(4.dp)

    Box(
        modifier = modifier
            .scale(waveScale)
            .clip(cellShape)
            .background(animatedColor)
            .then(
                if (isSelected) Modifier.border(
                    width = 1.5.dp,
                    color = Color.White.copy(alpha = pulseBorderAlpha),
                    shape = cellShape
                ) else Modifier
            )
    )
}
