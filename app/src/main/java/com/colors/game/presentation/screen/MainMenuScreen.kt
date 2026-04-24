package com.colors.game.presentation.screen

import android.app.Activity
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import kotlinx.coroutines.launch
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.collectAsState
import com.colors.game.presentation.viewmodel.MainMenuViewModel
import com.colors.game.ui.LocalStrings
import com.colors.game.ui.theme.*

@Composable
fun MainMenuScreen(
    onPlayNext: (Int) -> Unit,
    onResume: (Int) -> Unit,
    onSelectLevel: () -> Unit,
    onSettings: () -> Unit,
    onExit: () -> Unit,
    onDailyPuzzle: (String) -> Unit,
    onDailyCalendar: () -> Unit,
    viewModel: MainMenuViewModel = hiltViewModel()
) {
    val uiState  by viewModel.uiState.collectAsState()
    val strings  = LocalStrings.current
    val context  = LocalContext.current

    var showTutorial    by remember { mutableStateOf(false) }
    var pendingLevelId  by remember { mutableIntStateOf(1) }
    var showRedeemDialog by remember { mutableStateOf(false) }

    fun onPlayPressed() {
        pendingLevelId = uiState.nextLevelId
        if (!uiState.tutorialCompleted) {
            showTutorial = true
        } else {
            onPlayNext(uiState.nextLevelId)
        }
    }

    val gradient = Brush.verticalGradient(colors = listOf(Color(0xFF1A0533), Color(0xFF0D1B2A)))

    val infiniteRotation = rememberInfiniteTransition(label = "logoRotate")
    val rotation by infiniteRotation.animateFloat(
        initialValue  = 0f,
        targetValue   = 360f,
        animationSpec = infiniteRepeatable(tween(20_000, easing = LinearEasing)),
        label         = "logoRotation"
    )

    Box(
        modifier         = Modifier
            .fillMaxSize()
            .background(gradient)
            .systemBarsPadding()
            .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier            = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .padding(vertical = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Logo
            Box(
                modifier = Modifier
                    .size(88.dp)
                    .rotate(rotation)
                    .background(Primary.copy(alpha = 0.2f), RoundedCornerShape(24.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("●", fontSize = 48.sp)
            }

            Spacer(Modifier.height(4.dp))

            Text(
                text       = "Color Puzzles",
                style      = MaterialTheme.typography.displaySmall,
                color      = Color.White,
                fontWeight = FontWeight.ExtraBold,
                textAlign  = TextAlign.Center,
                maxLines   = 1,
                modifier   = Modifier.fillMaxWidth()
            )

            Text(
                text  = strings.levelsCompleted(uiState.totalCompleted),
                style = MaterialTheme.typography.bodyMedium,
                color = OnSurfaceDim
            )

            // Premium badge — only shown when user owns the upgrade
            if (uiState.isPremium) {
                Row(
                    verticalAlignment     = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier              = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(StarColor.copy(alpha = 0.15f))
                        .padding(horizontal = 14.dp, vertical = 5.dp)
                ) {
                    Icon(
                        imageVector        = Icons.Default.WorkspacePremium,
                        contentDescription = null,
                        tint               = StarColor,
                        modifier           = Modifier.size(16.dp)
                    )
                    Text(
                        text       = strings.premiumOwned,
                        style      = MaterialTheme.typography.labelLarge,
                        color      = StarColor,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(Modifier.height(8.dp))

            // Play / Next level button (with optional in-progress bar)
            val playText = if (uiState.hasNeverPlayed) strings.start
                           else strings.nextLevel(uiState.nextLevelId)
            val playIcon = if (uiState.hasNeverPlayed) Icons.Default.PlayArrow
                           else Icons.Default.SkipNext
            Column(modifier = Modifier.fillMaxWidth()) {
                MenuButton(text = playText, icon = playIcon, color = Primary, onClick = { onPlayPressed() })
                uiState.activeGameCoverage?.let { coverage ->
                    Spacer(Modifier.height(3.dp))
                    LinearProgressIndicator(
                        progress         = { coverage },
                        modifier         = Modifier
                            .fillMaxWidth()
                            .height(5.dp)
                            .clip(RoundedCornerShape(50)),
                        color            = SuccessGreen,
                        trackColor       = Color.White.copy(alpha = 0.12f),
                        strokeCap        = StrokeCap.Round
                    )
                }
            }

            // Resume (only if there's an in-progress game different from next)
            if (uiState.resumableLevelId != null &&
                uiState.resumableLevelId != uiState.nextLevelId) {
                MenuButton(
                    text    = strings.resumeLevel(uiState.resumableLevelId!!),
                    icon    = Icons.Default.Restore,
                    color   = Color(0xFF00897B),
                    onClick = { onResume(uiState.resumableLevelId!!) }
                )
            }

            MenuButton(
                text    = strings.levels,
                icon    = Icons.Default.GridView,
                color   = Color(0xFF5E35B1),
                onClick = onSelectLevel
            )

            // Daily puzzle button
            DailyButton(
                playedToday = uiState.dailyPlayedToday,
                labelPlay   = strings.dailyPuzzle,
                labelSee    = strings.dailyCalendar,
                onClick     = {
                    if (uiState.dailyPlayedToday) {
                        onDailyCalendar()
                    } else {
                        // Show ad BEFORE entering the game, then navigate
                        viewModel.requestDailyStartAd(context as Activity) {
                            onDailyPuzzle(uiState.todayKey)
                        }
                    }
                }
            )

            // Premium upgrade button + redeem link — hidden once purchased
            if (!uiState.isPremium) {
                PremiumButton(
                    label    = strings.getPremium,
                    subLabel = strings.premiumDesc,
                    onClick  = { viewModel.purchasePremium(context as Activity) }
                )
                TextButton(
                    onClick  = { showRedeemDialog = true },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Icon(
                        imageVector        = Icons.Default.ConfirmationNumber,
                        contentDescription = null,
                        tint               = OnSurfaceDim,
                        modifier           = Modifier.size(15.dp)
                    )
                    Spacer(Modifier.width(5.dp))
                    Text(
                        text  = strings.redeemCode,
                        color = OnSurfaceDim,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            MenuButton(
                text    = strings.settings,
                icon    = Icons.Default.Settings,
                color   = Color(0xFF37474F),
                onClick = onSettings
            )

            MenuButton(
                text    = strings.exit,
                icon    = Icons.Default.ExitToApp,
                color   = Color(0xFF424242),
                onClick = { (context as? Activity)?.finish() }
            )
        }

        // Redeem code dialog
        if (showRedeemDialog) {
            RedeemCodeDialog(
                strings   = strings,
                viewModel = viewModel,
                onDismiss = { showRedeemDialog = false }
            )
        }

        // Tutorial overlay
        AnimatedVisibility(
            visible = showTutorial,
            enter   = fadeIn(tween(300)) + scaleIn(tween(300), initialScale = 0.95f),
            exit    = fadeOut(tween(200)) + scaleOut(tween(200), targetScale = 0.95f)
        ) {
            TutorialOverlay(
                onComplete = {
                    showTutorial = false
                    viewModel.markTutorialCompleted()
                    onPlayNext(pendingLevelId)
                }
            )
        }
    }
}

@Composable
private fun MenuButton(
    text: String,
    icon: ImageVector,
    color: Color,
    onClick: () -> Unit
) {
    Button(
        onClick   = onClick,
        modifier  = Modifier.fillMaxWidth().height(56.dp),
        colors    = ButtonDefaults.buttonColors(containerColor = color),
        shape     = RoundedCornerShape(16.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
    ) {
        Icon(icon, contentDescription = null, modifier = Modifier.size(22.dp))
        Spacer(Modifier.width(12.dp))
        Text(text, style = MaterialTheme.typography.titleMedium, color = Color.White)
    }
}

@Composable
private fun RedeemCodeDialog(
    strings: com.colors.game.ui.AppStrings,
    viewModel: MainMenuViewModel,
    onDismiss: () -> Unit
) {
    var code       by remember { mutableStateOf("") }
    var errorMsg   by remember { mutableStateOf<String?>(null) }
    var successMsg by remember { mutableStateOf<String?>(null) }
    val scope      = rememberCoroutineScope()

    fun tryRedeem() {
        scope.launch {
            val valid = viewModel.redeemCode(code)
            if (valid) {
                successMsg = strings.redeemCodeSuccess
                errorMsg   = null
            } else {
                errorMsg   = strings.redeemCodeError
                successMsg = null
            }
        }
    }

    AlertDialog(
        onDismissRequest  = onDismiss,
        containerColor    = Color(0xFF1E1035),
        title = {
            Text(
                text  = strings.redeemCodeTitle,
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value         = code,
                    onValueChange = { code = it.uppercase(); errorMsg = null; successMsg = null },
                    placeholder   = { Text(strings.redeemCodeHint, color = OnSurfaceDim) },
                    singleLine    = true,
                    modifier      = Modifier.fillMaxWidth(),
                    colors        = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor   = Primary,
                        unfocusedBorderColor = OnSurfaceDim.copy(alpha = 0.4f),
                        focusedTextColor     = Color.White,
                        unfocusedTextColor   = Color.White,
                        cursorColor          = Primary
                    ),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Characters,
                        imeAction      = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = { tryRedeem() })
                )
                // Feedback messages
                successMsg?.let {
                    Text(it, color = SuccessGreen,
                        style = MaterialTheme.typography.bodySmall)
                }
                errorMsg?.let {
                    Text(it, color = ErrorRed,
                        style = MaterialTheme.typography.bodySmall)
                }
            }
        },
        confirmButton = {
            if (successMsg != null) {
                // After success, only show Close
                TextButton(onClick = onDismiss) {
                    Text(strings.back, color = Primary)
                }
            } else {
                Button(
                    onClick  = { tryRedeem() },
                    enabled  = code.isNotBlank(),
                    colors   = ButtonDefaults.buttonColors(containerColor = Primary)
                ) {
                    Text(strings.redeemCodeButton, color = Color.White)
                }
            }
        },
        dismissButton = {
            if (successMsg == null) {
                TextButton(onClick = onDismiss) {
                    Text(strings.back, color = OnSurfaceDim)
                }
            }
        }
    )
}

@Composable
private fun DailyButton(
    playedToday: Boolean,
    labelPlay: String,
    labelSee: String,
    onClick: () -> Unit
) {
    val teal = Color(0xFF00838F)
    Button(
        onClick   = onClick,
        modifier  = Modifier.fillMaxWidth().height(56.dp),
        colors    = ButtonDefaults.buttonColors(containerColor = teal),
        shape     = RoundedCornerShape(16.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
    ) {
        Icon(
            imageVector        = Icons.Default.CalendarMonth,
            contentDescription = null,
            modifier           = Modifier.size(22.dp)
        )
        Spacer(Modifier.width(12.dp))
        Text(
            text  = if (playedToday) labelSee else labelPlay,
            style = MaterialTheme.typography.titleMedium,
            color = Color.White
        )
        if (!playedToday) {
            Spacer(Modifier.width(8.dp))
            // Subtle "new" badge
            Text(
                text     = "NEW",
                style    = MaterialTheme.typography.labelSmall,
                color    = teal,
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(Color.White.copy(alpha = 0.9f))
                    .padding(horizontal = 6.dp, vertical = 1.dp)
            )
        }
    }
}

@Composable
private fun PremiumButton(
    label: String,
    subLabel: String,
    onClick: () -> Unit
) {
    val goldDark  = Color(0xFFAA8500)
    val goldLight = Color(0xFFFFD600)

    Button(
        onClick   = onClick,
        modifier  = Modifier.fillMaxWidth().height(56.dp),
        colors    = ButtonDefaults.buttonColors(containerColor = goldDark),
        shape     = RoundedCornerShape(16.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
    ) {
        Icon(
            imageVector        = Icons.Default.WorkspacePremium,
            contentDescription = null,
            tint               = goldLight,
            modifier           = Modifier.size(24.dp)
        )
        Spacer(Modifier.width(12.dp))
        Column {
            Text(
                text       = label,
                style      = MaterialTheme.typography.titleMedium,
                color      = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text(
                text  = subLabel,
                style = MaterialTheme.typography.labelSmall,
                color = goldLight.copy(alpha = 0.85f)
            )
        }
    }
}
