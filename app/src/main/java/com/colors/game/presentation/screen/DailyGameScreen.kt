package com.colors.game.presentation.screen

import android.app.Activity
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.LinearEasing
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.getSystemService
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.collectAsState
import com.colors.game.data.model.GameColor
import com.colors.game.presentation.component.ColorPicker
import com.colors.game.presentation.component.GameGrid
import com.colors.game.presentation.component.StarsDisplay
import com.colors.game.presentation.viewmodel.AdTrigger
import com.colors.game.presentation.viewmodel.DailyGameViewModel
import com.colors.game.ui.LocalStrings
import com.colors.game.ui.theme.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun DailyGameScreen(
    onNavigateMenu: () -> Unit,
    onNavigateCalendar: () -> Unit,
    viewModel: DailyGameViewModel = hiltViewModel()
) {
    val uiState  by viewModel.uiState.collectAsState()
    val strings  = LocalStrings.current
    val context  = LocalContext.current
    val activity = context as? Activity

    // Vibration helper
    val vibrate: () -> Unit = remember(context) {
        {
            val vibrator = context.getSystemService<Vibrator>()
            if (vibrator != null && uiState.settings.vibrationEnabled) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator.vibrate(VibrationEffect.createOneShot(18, VibrationEffect.DEFAULT_AMPLITUDE))
                } else {
                    @Suppress("DEPRECATION") vibrator.vibrate(18)
                }
            }
        }
    }

    // Show ad when flag is set (daily start or level completion from calendar)
    LaunchedEffect(uiState.showAd) {
        if (uiState.showAd && activity != null) viewModel.requestAd(activity)
    }

    val gameState = uiState.gameState
    val gradient  = Brush.verticalGradient(listOf(Color(0xFF1A0533), Color(0xFF0D1B2A)))

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .systemBarsPadding()
    ) {
        if (uiState.isLoading || gameState == null) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color    = Primary
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                // HUD with daily label
                DailyHUD(
                    dateKey        = uiState.dateKey,
                    isToday        = uiState.isToday,
                    difficulty     = strings.difficultyLabel(gameState.difficulty),
                    movesRemaining = gameState.movesRemaining,
                    maxMoves       = gameState.maxMoves,
                    timeElapsed    = gameState.timeElapsedSeconds,
                    movesLabel     = strings.moves,
                    timeLabel      = strings.time,
                    pauseLabel     = strings.pause,
                    onPause        = { viewModel.togglePause() }
                )

                Spacer(Modifier.height(12.dp))

                BoxWithConstraints(
                    modifier         = Modifier.weight(1f).fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    val gridSize = minOf(maxWidth, maxHeight) - 8.dp
                    GameGrid(
                        rows           = gameState.rows,
                        cols           = gameState.cols,
                        cells          = gameState.currentCells,
                        selectedGroup  = gameState.selectedGroup.toSet(),
                        animatingCells = uiState.animatingCells,
                        daltonicMode   = uiState.settings.daltonicMode,
                        modifier       = Modifier.size(gridSize)
                    )
                }

                Spacer(Modifier.height(16.dp))

                val activeColor = gameState.selectedGroup.firstOrNull()?.let { pos ->
                    gameState.currentCells[pos.row * gameState.cols + pos.col]
                }
                ColorPicker(
                    palette         = gameState.colorPalette,
                    activeColor     = activeColor,
                    daltonicMode    = uiState.settings.daltonicMode,
                    onColorSelected = { color -> vibrate(); viewModel.applyColor(color) },
                    modifier        = Modifier.padding(bottom = 8.dp)
                )
            }

            // Pause overlay
            AnimatedVisibility(
                visible = uiState.showPauseMenu,
                enter   = fadeIn(tween(180)),
                exit    = fadeOut(tween(120))
            ) {
                PauseMenuOverlay(
                    title      = strings.pausedTitle,
                    resumeBtn  = strings.resume,
                    restartBtn = strings.restart,
                    menuBtn    = strings.mainMenu,
                    onResume   = { viewModel.togglePause() },
                    onRestart  = { viewModel.restartLevel() },
                    onMenu     = onNavigateMenu
                )
            }

            // Result dialog
            AnimatedVisibility(
                visible = uiState.showResultDialog,
                enter   = fadeIn(tween(180)) + scaleIn(tween(180), initialScale = 0.92f),
                exit    = fadeOut(tween(120))
            ) {
                DailyResultDialog(
                    isWin        = gameState.isCompleted,
                    stars        = gameState.computeStars(),
                    completedStr = strings.completed,
                    gameOverStr  = strings.gameOver,
                    noMovesStr   = strings.noMovesLeft,
                    tryAgainStr  = strings.tryAgain,
                    mainMenuStr  = strings.mainMenu,
                    calendarStr  = strings.dailyCalendar,
                    onRestart    = { viewModel.restartLevel() },
                    onMenu       = onNavigateMenu,
                    onCalendar   = onNavigateCalendar
                )
            }
        }
    }

    // Lifecycle: pause timer when app goes to background
    DisposableEffect(Unit) {
        onDispose { viewModel.onBackground() }
    }
}

// ── Daily HUD ──────────────────────────────────────────────────────────────────

@Composable
private fun DailyHUD(
    dateKey: String,
    isToday: Boolean,
    difficulty: String,
    movesRemaining: Int,
    maxMoves: Int,
    timeElapsed: Int,
    movesLabel: String,
    timeLabel: String,
    pauseLabel: String,
    onPause: () -> Unit
) {
    val formatted = runCatching {
        val date = LocalDate.parse(dateKey)
        date.format(DateTimeFormatter.ofPattern("d MMM yyyy", Locale.getDefault()))
    }.getOrElse { dateKey }

    val timeStr = "%02d:%02d".format(timeElapsed / 60, timeElapsed % 60)
    val movesColor = when {
        movesRemaining <= 3            -> ErrorRed
        movesRemaining <= maxMoves / 3 -> StarColor
        else                           -> Color.White
    }

    Row(
        modifier              = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment     = Alignment.CenterVertically
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector        = Icons.Default.CalendarMonth,
                    contentDescription = null,
                    tint               = Primary,
                    modifier           = Modifier.size(16.dp)
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    text       = if (isToday) "Today · $difficulty" else "$formatted · $difficulty",
                    style      = MaterialTheme.typography.titleMedium,
                    color      = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            }
            if (!isToday) {
                Text(
                    text  = formatted,
                    style = MaterialTheme.typography.bodySmall,
                    color = OnSurfaceDim
                )
            }
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text  = "$movesRemaining",
                style = MaterialTheme.typography.titleLarge,
                color = movesColor,
                fontWeight = FontWeight.Bold
            )
            Text(text = movesLabel, style = MaterialTheme.typography.labelSmall, color = OnSurfaceDim)
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text  = timeStr,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace
                ),
                color = Color.White
            )
            Text(text = timeLabel, style = MaterialTheme.typography.labelSmall, color = OnSurfaceDim)
        }

        IconButton(onClick = onPause) {
            Icon(
                imageVector        = Icons.Default.Pause,
                contentDescription = pauseLabel,
                tint               = Color.White
            )
        }
    }
}

// ── Daily Result Dialog ────────────────────────────────────────────────────────

@Composable
private fun DailyResultDialog(
    isWin: Boolean,
    stars: Int,
    completedStr: String,
    gameOverStr: String,
    noMovesStr: String,
    tryAgainStr: String,
    mainMenuStr: String,
    calendarStr: String,
    onRestart: () -> Unit,
    onMenu: () -> Unit,
    onCalendar: () -> Unit
) {
    Box(
        modifier         = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.75f)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier            = Modifier
                .fillMaxWidth(0.85f)
                .clip(RoundedCornerShape(28.dp))
                .background(SurfaceCard)
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text      = if (isWin) completedStr else gameOverStr,
                style     = MaterialTheme.typography.headlineLarge,
                color     = if (isWin) SuccessGreen else ErrorRed,
                textAlign = TextAlign.Center
            )

            if (isWin) {
                StarsDisplay(stars = stars, animated = true)
            } else {
                Text(
                    text  = noMovesStr,
                    style = MaterialTheme.typography.bodyLarge,
                    color = OnSurfaceDim
                )
            }

            Spacer(Modifier.height(4.dp))

            if (isWin) {
                Button(
                    onClick  = onCalendar,
                    modifier = Modifier.fillMaxWidth().height(52.dp),
                    colors   = ButtonDefaults.buttonColors(containerColor = Primary),
                    shape    = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Default.CalendarMonth, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(8.dp))
                    Text(calendarStr, style = MaterialTheme.typography.titleMedium)
                }
            }

            OutlinedButton(
                onClick  = onRestart,
                modifier = Modifier.fillMaxWidth().height(52.dp),
                shape    = RoundedCornerShape(12.dp)
            ) {
                Text(tryAgainStr, style = MaterialTheme.typography.titleMedium, color = Color.White)
            }

            TextButton(onClick = onMenu) {
                Text(mainMenuStr, color = OnSurfaceDim)
            }
        }
    }
}

// ── Helpers ────────────────────────────────────────────────────────────────────

private fun com.colors.game.ui.AppStrings.difficultyLabel(
    difficulty: com.colors.game.data.model.Difficulty
): String = when (difficulty) {
    com.colors.game.data.model.Difficulty.EASY   -> easy
    com.colors.game.data.model.Difficulty.MEDIUM -> medium
    com.colors.game.data.model.Difficulty.HARD   -> hard
}
