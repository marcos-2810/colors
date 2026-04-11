package com.colors.game.presentation.screen

import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.getSystemService
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.colors.game.data.model.GameColor
import com.colors.game.data.model.Position
import com.colors.game.domain.LevelGenerator
import com.colors.game.presentation.component.*
import com.colors.game.presentation.viewmodel.GameViewModel
import com.colors.game.ui.theme.*

@Composable
fun GameScreen(
    onNavigateBack: () -> Unit,
    onNavigateMenu: () -> Unit,
    onNavigateNext: (Int) -> Unit,
    viewModel: GameViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    // Haptic feedback on color apply
    fun vibrate() {
        if (uiState.settings.vibrationEnabled) {
            context.getSystemService<Vibrator>()?.let { vib ->
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vib.vibrate(VibrationEffect.createOneShot(30, VibrationEffect.DEFAULT_AMPLITUDE))
                }
            }
        }
    }

    // Lifecycle: pause when leaving screen, resume when returning
    DisposableEffect(Unit) {
        onDispose { viewModel.onBackground() }
    }

    val gameState = uiState.gameState

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .systemBarsPadding()
    ) {
        if (uiState.isLoading || gameState == null) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            return@Box
        }

        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // ── HUD: level info, timer, moves ──────────────────────────
            GameHUD(
                levelId         = gameState.levelId,
                difficulty      = gameState.difficulty.displayName,
                movesRemaining  = gameState.movesRemaining,
                maxMoves        = gameState.maxMoves,
                timeRemaining   = gameState.timeRemainingSeconds,
                onPause         = { viewModel.togglePause() }
            )

            Spacer(Modifier.height(12.dp))

            // ── Grid ───────────────────────────────────────────────────
            BoxWithConstraints(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                val gridSize = minOf(maxWidth, maxHeight) - 8.dp

                GameGrid(
                    rows          = gameState.rows,
                    cols          = gameState.cols,
                    cells         = gameState.currentCells,
                    selectedGroup = gameState.selectedGroup.toSet(),
                    animatingCells = uiState.animatingCells,
                    daltonicMode  = uiState.settings.daltonicMode,
                    onCellTap     = { row, col -> viewModel.selectCell(row, col) },
                    modifier      = Modifier.size(gridSize)
                )
            }

            Spacer(Modifier.height(16.dp))

            // ── Color picker ───────────────────────────────────────────
            val activeColor = gameState.selectedGroup.firstOrNull()?.let { pos ->
                gameState.currentCells[pos.row * gameState.cols + pos.col]
            }
            ColorPicker(
                palette         = gameState.colorPalette,
                activeColor     = activeColor,
                daltonicMode    = uiState.settings.daltonicMode,
                onColorSelected = { color ->
                    vibrate()
                    viewModel.applyColor(color)
                },
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        // ── Pause overlay ──────────────────────────────────────────────
        AnimatedVisibility(
            visible = uiState.showPauseMenu,
            enter   = fadeIn() + scaleIn(initialScale = 0.9f),
            exit    = fadeOut() + scaleOut(targetScale = 0.9f)
        ) {
            PauseMenuOverlay(
                onResume  = { viewModel.togglePause() },
                onRestart = { viewModel.restartLevel() },
                onMenu    = onNavigateMenu
            )
        }

        // ── Result dialog ──────────────────────────────────────────────
        AnimatedVisibility(
            visible = uiState.showResultDialog,
            enter   = fadeIn() + scaleIn(initialScale = 0.85f),
            exit    = fadeOut()
        ) {
            ResultDialog(
                isWin     = gameState.isCompleted,
                stars     = gameState.computeStars(),
                levelId   = gameState.levelId,
                onRestart = { viewModel.restartLevel() },
                onMenu    = onNavigateMenu,
                onNext    = {
                    val nextId = (gameState.levelId + 1).coerceAtMost(LevelGenerator.TOTAL_LEVELS)
                    if (nextId > gameState.levelId) onNavigateNext(nextId) else onNavigateMenu()
                }
            )
        }
    }
}

// ── HUD ────────────────────────────────────────────────────────────────────

@Composable
private fun GameHUD(
    levelId: Int,
    difficulty: String,
    movesRemaining: Int,
    maxMoves: Int,
    timeRemaining: Int,
    onPause: () -> Unit
) {
    val minutes = timeRemaining / 60
    val seconds = timeRemaining % 60
    val timeStr = "%02d:%02d".format(minutes, seconds)

    val movesColor = when {
        movesRemaining <= 3 -> ErrorRed
        movesRemaining <= maxMoves / 3 -> StarColor
        else -> Color.White
    }

    val timeColor = when {
        timeRemaining <= 30 -> ErrorRed
        timeRemaining <= 60 -> StarColor
        else -> Color.White
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Level + difficulty
        Column {
            Text(
                text  = "Nivel $levelId",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )
            Text(
                text  = difficulty,
                style = MaterialTheme.typography.bodyMedium,
                color = OnSurfaceDim
            )
        }

        // Moves
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text  = movesRemaining.toString(),
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold),
                color = movesColor
            )
            Text(text = "movimientos", style = MaterialTheme.typography.labelLarge, color = OnSurfaceDim)
        }

        // Timer
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text  = timeStr,
                style = MaterialTheme.typography.titleLarge.copy(fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace),
                color = timeColor
            )
            Text(text = "tiempo", style = MaterialTheme.typography.labelLarge, color = OnSurfaceDim)
        }

        // Pause button
        IconButton(onClick = onPause) {
            Icon(Icons.Filled.Pause, contentDescription = "Pausa", tint = Color.White)
        }
    }
}

// ── Pause menu ─────────────────────────────────────────────────────────────

@Composable
private fun PauseMenuOverlay(
    onResume: () -> Unit,
    onRestart: () -> Unit,
    onMenu: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.75f)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(24.dp))
                .background(SurfaceCard)
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Pausa", style = MaterialTheme.typography.headlineLarge, color = Color.White)

            PauseButton("Reanudar", Primary, onResume)
            PauseButton("Reiniciar", Color(0xFF795548), onRestart)
            PauseButton("Menú principal", Color(0xFF37474F), onMenu)
        }
    }
}

@Composable
private fun PauseButton(text: String, color: Color, onClick: () -> Unit) {
    Button(
        onClick  = onClick,
        modifier = Modifier.fillMaxWidth().height(52.dp),
        colors   = ButtonDefaults.buttonColors(containerColor = color),
        shape    = RoundedCornerShape(12.dp)
    ) {
        Text(text, style = MaterialTheme.typography.titleMedium, color = Color.White)
    }
}

// ── Result dialog ──────────────────────────────────────────────────────────

@Composable
private fun ResultDialog(
    isWin: Boolean,
    stars: Int,
    levelId: Int,
    onRestart: () -> Unit,
    onMenu: () -> Unit,
    onNext: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.75f)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .clip(RoundedCornerShape(28.dp))
                .background(SurfaceCard)
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text      = if (isWin) "¡Completado!" else "¡Fin del juego!",
                style     = MaterialTheme.typography.headlineLarge,
                color     = if (isWin) SuccessGreen else ErrorRed,
                textAlign = TextAlign.Center
            )

            if (isWin) {
                StarsDisplay(stars = stars, animated = true)
            } else {
                Text(
                    text  = if (stars == 0) "Sin movimientos" else "¡Tiempo agotado!",
                    style = MaterialTheme.typography.bodyLarge,
                    color = OnSurfaceDim
                )
            }

            Spacer(Modifier.height(4.dp))

            if (isWin && levelId < LevelGenerator.TOTAL_LEVELS) {
                Button(
                    onClick  = onNext,
                    modifier = Modifier.fillMaxWidth().height(52.dp),
                    colors   = ButtonDefaults.buttonColors(containerColor = SuccessGreen),
                    shape    = RoundedCornerShape(12.dp)
                ) {
                    Text("Siguiente nivel", style = MaterialTheme.typography.titleMedium)
                }
            }

            OutlinedButton(
                onClick  = onRestart,
                modifier = Modifier.fillMaxWidth().height(52.dp),
                shape    = RoundedCornerShape(12.dp)
            ) {
                Text("Intentar de nuevo", style = MaterialTheme.typography.titleMedium, color = Color.White)
            }

            TextButton(onClick = onMenu) {
                Text("Menú principal", color = OnSurfaceDim)
            }
        }
    }
}
