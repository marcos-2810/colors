package com.colors.game.presentation.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
 * Each cell:
 *  - Animates to its new color smoothly when changed.
 *  - Shows a pulsing highlight border when selected.
 *  - Plays a wave animation based on BFS distance from the changed group.
 *
 * Performance notes:
 *  - Uses key() for stable recomposition — only changed cells recompose.
 *  - Cell clip/shape is constant → no re-layout on every frame.
 *  - Animation specs use spring/tween to avoid jank.
 */
@Composable
fun GameGrid(
    rows: Int,
    cols: Int,
    cells: List<GameColor>,
    selectedGroup: Set<Position>,
    animatingCells: Map<Position, Int>,   // position → BFS wave distance
    daltonicMode: Boolean,
    onCellTap: (row: Int, col: Int) -> Unit,
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
                            onTap        = { onCellTap(row, col) },
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
    onTap: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Color transition — smooth, spring-based
    val animatedColor by animateColorAsState(
        targetValue = color.toComposeColor(daltonicMode),
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness    = Spring.StiffnessMedium
        ),
        label = "cellColor"
    )

    // Wave ripple: cells animate with a delay proportional to BFS distance
    val waveDelay = (waveDistance ?: 0) * 60  // 60ms per wave ring
    val waveScale by animateFloatAsState(
        targetValue = if (waveDistance != null) 1f else 1f,
        animationSpec = if (waveDistance != null) {
            tween(
                durationMillis = 300,
                delayMillis    = waveDelay,
                easing         = FastOutSlowInEasing
            )
        } else {
            snap()
        },
        label = "waveScale"
    )

    // Selection pulse animation
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val pulseBorderAlpha by infiniteTransition.animateFloat(
        initialValue = 0.6f,
        targetValue  = 1.0f,
        animationSpec = infiniteRepeatable(
            animation  = tween(600, easing = FastOutSlowInEasing),
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
                    width = 2.dp,
                    color = Color.White.copy(alpha = pulseBorderAlpha),
                    shape = cellShape
                ) else Modifier
            )
            .clickable(onClick = onTap)
    )
}
