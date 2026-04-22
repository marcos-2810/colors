package com.colors.game.presentation.screen

import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
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
import androidx.compose.runtime.collectAsState
import com.colors.game.data.model.GameColor
import com.colors.game.data.model.Position
import com.colors.game.domain.LevelGenerator
import com.colors.game.presentation.component.*
import com.colors.game.presentation.viewmodel.GameViewModel
import com.colors.game.presentation.viewmodel.LeaderboardViewModel
import com.colors.game.ui.LocalStrings
import com.colors.game.ui.difficultyName
import com.colors.game.ui.theme.*

@Composable
fun GameScreen(
    onNavigateBack: () -> Unit,
    onNavigateMenu: () -> Unit,
    onNavigateNext: (Int) -> Unit,
    viewModel: GameViewModel = hiltViewModel(),
    leaderboardVm: LeaderboardViewModel = hiltViewModel()
) {
    val uiState  by viewModel.uiState.collectAsState()
    val context  = LocalContext.current
    val activity = context as? android.app.Activity
    val strings  = LocalStrings.current

    // Show interstitial ad when the ViewModel requests it
    LaunchedEffect(uiState.showAd) {
        if (uiState.showAd && activity != null) {
            viewModel.requestAd(activity)
        }
    }

    fun vibrate() {
        if (uiState.settings.vibrationEnabled) {
            context.getSystemService<Vibrator>()?.let { vib ->
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vib.vibrate(VibrationEffect.createOneShot(30, VibrationEffect.DEFAULT_AMPLITUDE))
                }
            }
        }
    }

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

        } else {

            Column(
                modifier            = Modifier.fillMaxSize().padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                GameHUD(
                    levelId        = gameState.levelId,
                    difficulty     = strings.difficultyName(gameState.difficulty),
                    movesRemaining = gameState.movesRemaining,
                    maxMoves       = gameState.maxMoves,
                    timeElapsed    = gameState.timeElapsedSeconds,
                    movesLabel     = strings.moves,
                    timeLabel      = strings.time,
                    pauseLabel     = strings.pause,
                    levelLabel     = strings.levelN(gameState.levelId),
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
                enter   = fadeIn() + scaleIn(initialScale = 0.9f),
                exit    = fadeOut() + scaleOut(targetScale = 0.9f)
            ) {
                PauseMenuOverlay(
                    title     = strings.pausedTitle,
                    resumeBtn = strings.resume,
                    restartBtn = strings.restart,
                    menuBtn   = strings.mainMenu,
                    onResume  = { viewModel.togglePause() },
                    onRestart = { viewModel.restartLevel() },
                    onMenu    = onNavigateMenu
                )
            }

            // Result dialog
            AnimatedVisibility(
                visible = uiState.showResultDialog,
                enter   = fadeIn() + scaleIn(initialScale = 0.85f),
                exit    = fadeOut()
            ) {
                ResultDialog(
                    isWin           = gameState.isCompleted,
                    stars           = gameState.computeStars(),
                    levelId         = gameState.levelId,
                    completedStr    = strings.completed,
                    gameOverStr     = strings.gameOver,
                    noMovesStr      = strings.noMovesLeft,
                    nextLevelStr    = strings.nextLevelBtn,
                    tryAgainStr     = strings.tryAgain,
                    mainMenuStr     = strings.mainMenu,
                    viewRankingsStr = strings.viewRankings,
                    onRestart    = { viewModel.restartLevel() },
                    onMenu       = onNavigateMenu,
                    onNext       = {
                        val nextId = (gameState.levelId + 1).coerceAtMost(LevelGenerator.TOTAL_LEVELS)
                        if (nextId > gameState.levelId) onNavigateNext(nextId) else onNavigateMenu()
                    },
                    onViewRankings = {
                        // Force-refresh so the score we just submitted appears
                        leaderboardVm.loadForLevel(gameState.levelId)
                        leaderboardVm.refresh()
                        viewModel.showLeaderboard()
                    }
                )
            }

            // Leaderboard sheet (shown after level completion)
            if (uiState.showLeaderboard) {
                LeaderboardSheet(
                    levelId   = gameState.levelId,
                    viewModel = leaderboardVm,
                    onDismiss = { viewModel.dismissLeaderboard() }
                )
            }
        }
    }
}

// ── HUD ────────────────────────────────────────────────────────────────────

@Composable
internal fun GameHUD(
    levelId: Int,
    difficulty: String,
    movesRemaining: Int,
    maxMoves: Int,
    timeElapsed: Int,
    movesLabel: String,
    timeLabel: String,
    pauseLabel: String,
    levelLabel: String,
    onPause: () -> Unit
) {
    val minutes  = timeElapsed / 60
    val seconds  = timeElapsed % 60
    val timeStr  = "%02d:%02d".format(minutes, seconds)

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
            Text(text = levelLabel,   style = MaterialTheme.typography.titleLarge,  color = Color.White)
            Text(text = difficulty,   style = MaterialTheme.typography.bodyMedium,  color = OnSurfaceDim)
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text  = movesRemaining.toString(),
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold),
                color = movesColor
            )
            Text(text = movesLabel, style = MaterialTheme.typography.labelLarge, color = OnSurfaceDim)
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text  = timeStr,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace
                ),
                color = Color.White
            )
            Text(text = timeLabel, style = MaterialTheme.typography.labelLarge, color = OnSurfaceDim)
        }

        IconButton(onClick = onPause) {
            Icon(Icons.Filled.Pause, contentDescription = pauseLabel, tint = Color.White)
        }
    }
}

// ── Pause menu ─────────────────────────────────────────────────────────────

@Composable
internal fun PauseMenuOverlay(
    title: String,
    resumeBtn: String,
    restartBtn: String,
    menuBtn: String,
    onResume: () -> Unit,
    onRestart: () -> Unit,
    onMenu: () -> Unit
) {
    Box(
        modifier         = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.75f)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier            = Modifier
                .clip(RoundedCornerShape(24.dp))
                .background(SurfaceCard)
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(title, style = MaterialTheme.typography.headlineLarge, color = Color.White)
            PauseButton(resumeBtn,  Primary,             onResume)
            PauseButton(restartBtn, Color(0xFF795548),   onRestart)
            PauseButton(menuBtn,    Color(0xFF37474F),   onMenu)
        }
    }
}

@Composable
internal fun PauseButton(text: String, color: Color, onClick: () -> Unit) {
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
internal fun ResultDialog(
    isWin: Boolean,
    stars: Int,
    levelId: Int,
    completedStr: String,
    gameOverStr: String,
    noMovesStr: String,
    nextLevelStr: String,
    tryAgainStr: String,
    mainMenuStr: String,
    viewRankingsStr: String,
    onRestart: () -> Unit,
    onMenu: () -> Unit,
    onNext: () -> Unit,
    onViewRankings: () -> Unit
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

            if (isWin && levelId < LevelGenerator.TOTAL_LEVELS) {
                Button(
                    onClick  = onNext,
                    modifier = Modifier.fillMaxWidth().height(52.dp),
                    colors   = ButtonDefaults.buttonColors(containerColor = SuccessGreen),
                    shape    = RoundedCornerShape(12.dp)
                ) {
                    Text(nextLevelStr, style = MaterialTheme.typography.titleMedium)
                }
            }

            OutlinedButton(
                onClick  = onRestart,
                modifier = Modifier.fillMaxWidth().height(52.dp),
                shape    = RoundedCornerShape(12.dp)
            ) {
                Text(tryAgainStr, style = MaterialTheme.typography.titleMedium, color = Color.White)
            }

            if (isWin) {
                OutlinedButton(
                    onClick  = onViewRankings,
                    modifier = Modifier.fillMaxWidth().height(52.dp),
                    shape    = RoundedCornerShape(12.dp),
                    colors   = ButtonDefaults.outlinedButtonColors(contentColor = Primary),
                    border   = BorderStroke(1.dp, Primary.copy(alpha = 0.6f))
                ) {
                    Icon(
                        imageVector        = Icons.Default.EmojiEvents,
                        contentDescription = null,
                        modifier           = Modifier.size(18.dp),
                        tint               = Primary
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(viewRankingsStr, style = MaterialTheme.typography.titleMedium, color = Primary)
                }
            }

            TextButton(onClick = onMenu) {
                Text(mainMenuStr, color = OnSurfaceDim)
            }
        }
    }
}
