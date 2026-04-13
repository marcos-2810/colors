package com.colors.game.presentation.screen

import androidx.compose.animation.*
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.colors.game.ui.AppStrings
import com.colors.game.ui.LocalStrings
import com.colors.game.ui.theme.*

// ──────────────────────────────────────────────────────────────────────────────
// Tutorial page model
// ──────────────────────────────────────────────────────────────────────────────
private data class TutorialPage(
    val title: String,
    val description: String,
    val illustration: @Composable () -> Unit
)

// ──────────────────────────────────────────────────────────────────────────────
// Tutorial overlay — shown on top of the main menu the first time the user plays.
// ──────────────────────────────────────────────────────────────────────────────
@Composable
fun TutorialOverlay(onComplete: () -> Unit) {
    val strings = LocalStrings.current
    val pages   = remember(strings) { buildTutorialPages(strings) }
    var currentPage by remember { mutableIntStateOf(0) }
    val isLastPage = currentPage == pages.lastIndex

    Box(
        modifier         = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.93f)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier            = Modifier
                .fillMaxWidth(0.92f)
                .clip(RoundedCornerShape(28.dp))
                .background(Color(0xFF1C1032))
                .padding(28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Header
            Row(
                modifier          = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text       = strings.howToPlay,
                    style      = MaterialTheme.typography.titleLarge,
                    color      = Color.White,
                    fontWeight = FontWeight.Bold
                )
                TextButton(onClick = onComplete) {
                    Text(strings.skip, color = OnSurfaceDim, style = MaterialTheme.typography.bodyMedium)
                }
            }

            // Illustration
            AnimatedContent(
                targetState  = currentPage,
                transitionSpec = {
                    (fadeIn(tween(300)) + slideInHorizontally(tween(300)) { it / 3 })
                        .togetherWith(fadeOut(tween(200)) + slideOutHorizontally(tween(200)) { -it / 3 })
                },
                label = "tutorialIllustration"
            ) { page ->
                Box(
                    modifier         = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xFF110820)),
                    contentAlignment = Alignment.Center
                ) {
                    pages[page].illustration()
                }
            }

            // Text
            AnimatedContent(
                targetState  = currentPage,
                transitionSpec = { fadeIn(tween(250)).togetherWith(fadeOut(tween(150))) },
                label        = "tutorialText"
            ) { page ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text       = pages[page].title,
                        style      = MaterialTheme.typography.headlineMedium,
                        color      = Color.White,
                        fontWeight = FontWeight.Bold,
                        textAlign  = TextAlign.Center
                    )
                    Text(
                        text       = pages[page].description,
                        style      = MaterialTheme.typography.bodyLarge,
                        color      = OnSurfaceDim,
                        textAlign  = TextAlign.Center,
                        lineHeight = 22.sp
                    )
                }
            }

            // Page indicators
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment     = Alignment.CenterVertically
            ) {
                pages.indices.forEach { i ->
                    val isActive = i == currentPage
                    val width by animateDpAsState(
                        targetValue   = if (isActive) 24.dp else 8.dp,
                        animationSpec = spring(stiffness = Spring.StiffnessMedium),
                        label         = "dotWidth"
                    )
                    Box(
                        modifier = Modifier
                            .height(8.dp)
                            .width(width)
                            .clip(CircleShape)
                            .background(if (isActive) Primary else Color.White.copy(alpha = 0.25f))
                    )
                }
            }

            // Navigation buttons
            Row(
                modifier              = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (currentPage > 0) {
                    OutlinedButton(
                        onClick  = { currentPage-- },
                        modifier = Modifier.weight(1f).height(52.dp),
                        shape    = RoundedCornerShape(14.dp),
                        colors   = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)
                    ) {
                        Text(strings.previous)
                    }
                }

                Button(
                    onClick  = { if (isLastPage) onComplete() else currentPage++ },
                    modifier = Modifier.weight(1f).height(52.dp),
                    shape    = RoundedCornerShape(14.dp),
                    colors   = ButtonDefaults.buttonColors(
                        containerColor = if (isLastPage) SuccessGreen else Primary
                    )
                ) {
                    if (isLastPage) {
                        Icon(Icons.Default.SportsEsports, contentDescription = null,
                            modifier = Modifier.size(20.dp))
                        Spacer(Modifier.width(8.dp))
                        Text(strings.letsPlay, fontWeight = FontWeight.Bold)
                    } else {
                        Text(strings.next)
                        Spacer(Modifier.width(6.dp))
                        Icon(Icons.Default.ArrowForward, contentDescription = null,
                            modifier = Modifier.size(18.dp))
                    }
                }
            }
        }
    }
}

// ──────────────────────────────────────────────────────────────────────────────
// Page definitions — titles and descriptions come from strings
// ──────────────────────────────────────────────────────────────────────────────
private fun buildTutorialPages(strings: AppStrings): List<TutorialPage> = listOf(
    TutorialPage(
        title        = strings.tutPage1Title,
        description  = strings.tutPage1Desc,
        illustration = { IllustrationTopLeft() }
    ),
    TutorialPage(
        title        = strings.tutPage2Title,
        description  = strings.tutPage2Desc,
        illustration = { IllustrationPickColor() }
    ),
    TutorialPage(
        title        = strings.tutPage3Title,
        description  = strings.tutPage3Desc,
        illustration = { IllustrationExpand() }
    ),
    TutorialPage(
        title        = strings.tutPage4Title,
        description  = strings.tutPage4Desc,
        illustration = { IllustrationWin() }
    )
)

// ──────────────────────────────────────────────────────────────────────────────
// Illustrations — each reads LocalStrings for its own labels
// ──────────────────────────────────────────────────────────────────────────────

/** Page 1: top-left corner always active */
@Composable
private fun IllustrationTopLeft() {
    val strings = LocalStrings.current
    val grid = listOf(
        listOf(0, 0, 1, 2),
        listOf(0, 1, 1, 2),
        listOf(3, 3, 1, 2),
        listOf(3, 2, 2, 2)
    )
    val colors = listOf(
        Color(0xFF1E88E5),
        Color(0xFFE53935),
        Color(0xFFFFD600),
        Color(0xFF43A047)
    )
    val activeGroup = setOf(0 to 0, 0 to 1, 1 to 0)

    val infiniteTransition = rememberInfiniteTransition(label = "topLeftPulse")
    val borderAlpha by infiniteTransition.animateFloat(
        initialValue  = 0.4f, targetValue = 1.0f,
        animationSpec = infiniteRepeatable(tween(700), RepeatMode.Reverse),
        label         = "borderAlpha"
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        grid.forEachIndexed { r, row ->
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                row.forEachIndexed { c, colorIdx ->
                    val isActive = (r to c) in activeGroup
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(colors[colorIdx])
                            .then(
                                if (isActive) Modifier.border(
                                    2.5.dp,
                                    Color.White.copy(alpha = borderAlpha),
                                    RoundedCornerShape(6.dp)
                                ) else Modifier
                            )
                    )
                }
            }
        }
        Spacer(Modifier.height(6.dp))
        Text(
            strings.tutCornerLabel,
            color     = Color.White.copy(alpha = 0.75f),
            style     = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center
        )
    }
}

/** Page 2: color picker with animated selection */
@Composable
private fun IllustrationPickColor() {
    val strings = LocalStrings.current
    val palette = listOf(
        Color(0xFFE53935), Color(0xFF43A047), Color(0xFF1E88E5),
        Color(0xFFFFD600), Color(0xFF6D4C41), Color(0xFFE91E8C)
    )
    var selected by remember { mutableIntStateOf(2) }

    val infiniteTransition = rememberInfiniteTransition(label = "arrowBounce")
    val arrowY by infiniteTransition.animateFloat(
        initialValue  = 0f, targetValue = 6f,
        animationSpec = infiniteRepeatable(tween(600, easing = FastOutSlowInEasing), RepeatMode.Reverse),
        label         = "arrowY"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(palette[selected])
                .border(2.dp, Color.White.copy(alpha = 0.4f), RoundedCornerShape(16.dp))
        )

        Text(
            strings.tutColorChangesTo,
            color    = Color.White.copy(alpha = 0.6f),
            style    = MaterialTheme.typography.bodySmall,
            modifier = Modifier.offset(y = arrowY.dp)
        )

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            palette.forEachIndexed { i, color ->
                val isActive = i == selected
                val scale by animateFloatAsState(
                    targetValue   = if (isActive) 1.2f else 0.9f,
                    animationSpec = spring(Spring.DampingRatioMediumBouncy),
                    label         = "btnScale"
                )
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .scale(scale)
                        .clip(CircleShape)
                        .background(color)
                        .border(
                            if (isActive) 3.dp else 1.dp,
                            if (isActive) Color.White else Color.White.copy(alpha = 0.3f),
                            CircleShape
                        )
                )
            }
        }
        Text(strings.tutTapColor, color = OnSurfaceDim, style = MaterialTheme.typography.bodySmall)
    }

    LaunchedEffect(Unit) {
        while (true) {
            kotlinx.coroutines.delay(1200)
            selected = (selected + 1) % palette.size
        }
    }
}

/** Page 3: group expansion animation */
@Composable
private fun IllustrationExpand() {
    val strings = LocalStrings.current
    var showExpanded by remember { mutableStateOf(false) }

    val blue   = Color(0xFF1E88E5)
    val green  = Color(0xFF43A047)
    val red    = Color(0xFFE53935)
    val yellow = Color(0xFFFFD600)

    // BEFORE: blue group at top-left (3 cells), surrounded by adjacent green cells
    val beforeGrid = listOf(
        listOf(blue,   blue,   red,    red),
        listOf(blue,   green,  green,  red),
        listOf(yellow, green,  green,  red),
        listOf(yellow, yellow, red,    red)
    )
    val beforeActive = setOf(0 to 0, 0 to 1, 1 to 0)

    // AFTER: blue → green, group absorbs all adjacent greens (7 cells)
    val afterGrid = listOf(
        listOf(green,  green,  red,    red),
        listOf(green,  green,  green,  red),
        listOf(yellow, green,  green,  red),
        listOf(yellow, yellow, red,    red)
    )
    val afterActive = setOf(0 to 0, 0 to 1, 1 to 0, 1 to 1, 1 to 2, 2 to 1, 2 to 2)

    val currentGrid   = if (showExpanded) afterGrid   else beforeGrid
    val currentActive = if (showExpanded) afterActive else beforeActive

    LaunchedEffect(Unit) {
        while (true) {
            kotlinx.coroutines.delay(1600)
            showExpanded = !showExpanded
        }
    }

    val infiniteTransition = rememberInfiniteTransition(label = "expandPulse")
    val borderAlpha by infiniteTransition.animateFloat(
        initialValue  = 0.45f, targetValue = 1.0f,
        animationSpec = infiniteRepeatable(tween(650), RepeatMode.Reverse),
        label         = "borderAlpha"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            if (showExpanded) strings.tutExpandedN(7) else strings.tutInitialGroupN(3),
            color      = if (showExpanded) SuccessGreen else Color.White,
            style      = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            currentGrid.forEachIndexed { r, row ->
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    row.forEachIndexed { c, color ->
                        val isActive = (r to c) in currentActive
                        val animColor by animateColorAsState(
                            targetValue   = color,
                            animationSpec = tween(400),
                            label         = "cellColor"
                        )
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(animColor)
                                .then(
                                    if (isActive) Modifier.border(
                                        2.dp,
                                        Color.White.copy(alpha = borderAlpha),
                                        RoundedCornerShape(6.dp)
                                    ) else Modifier
                                )
                        )
                    }
                }
            }
        }

        Text(
            if (showExpanded) strings.tutAfterExpandDesc else strings.tutBeforeExpandDesc,
            color     = OnSurfaceDim,
            style     = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center
        )
    }
}

/** Page 4: completed board with win animation */
@Composable
private fun IllustrationWin() {
    val strings = LocalStrings.current
    val infiniteTransition = rememberInfiniteTransition(label = "winGlow")
    val glow by infiniteTransition.animateFloat(
        initialValue  = 0.6f, targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(800, easing = FastOutSlowInEasing), RepeatMode.Reverse),
        label         = "glowAlpha"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            repeat(4) {
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    repeat(4) {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(SuccessGreen)
                                .border(1.5.dp, Color.White.copy(alpha = glow * 0.5f),
                                    RoundedCornerShape(6.dp))
                        )
                    }
                }
            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            repeat(3) {
                Text("★", fontSize = 32.sp, color = StarColor.copy(alpha = glow))
            }
        }

        Text(
            strings.tutWinDesc,
            color     = OnSurfaceDim,
            style     = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center
        )
    }
}
