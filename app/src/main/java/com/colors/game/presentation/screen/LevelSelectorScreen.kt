package com.colors.game.presentation.screen

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.collectAsState
import com.colors.game.data.model.Difficulty
import com.colors.game.presentation.component.StarsDisplay
import com.colors.game.presentation.viewmodel.LevelItem
import com.colors.game.presentation.viewmodel.LevelSelectorViewModel
import com.colors.game.presentation.viewmodel.LeaderboardViewModel
import com.colors.game.ui.LocalStrings
import com.colors.game.ui.difficultyName
import com.colors.game.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LevelSelectorScreen(
    onLevelSelected: (Int) -> Unit,
    onBack: () -> Unit,
    viewModel: LevelSelectorViewModel = hiltViewModel(),
    leaderboardVm: LeaderboardViewModel = hiltViewModel()
) {
    val uiState  by viewModel.uiState.collectAsState()
    val strings  = LocalStrings.current
    val activity = LocalContext.current as? Activity

    var selectedItem by remember { mutableStateOf<LevelItem?>(null) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .systemBarsPadding()
    ) {
        // ── Top bar ──────────────────────────────────────────────────────────
        Row(
            modifier          = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.Default.ArrowBack, contentDescription = strings.back, tint = Color.White)
            }
            Text(
                text     = strings.selectLevel,
                style    = MaterialTheme.typography.headlineMedium,
                color    = Color.White,
                modifier = Modifier.weight(1f).padding(start = 4.dp)
            )
        }

        // ── Difficulty tabs ──────────────────────────────────────────────────
        Row(
            modifier              = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Difficulty.entries.forEach { diff ->
                val selected = uiState.selectedDifficulty == diff
                val tabColor = when (diff) {
                    Difficulty.EASY   -> Color(0xFF43A047)
                    Difficulty.MEDIUM -> Color(0xFFF57C00)
                    Difficulty.HARD   -> Color(0xFFE53935)
                }
                FilterChip(
                    selected = selected,
                    onClick  = { viewModel.selectDifficulty(diff) },
                    label    = { Text(strings.difficultyName(diff)) },
                    colors   = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = tabColor,
                        selectedLabelColor     = Color.White
                    ),
                    modifier = Modifier.weight(1f)
                )
            }
        }

        Spacer(Modifier.height(8.dp))

        if (uiState.isLoading) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Primary)
            }
        } else {
            LazyVerticalGrid(
                columns               = GridCells.Fixed(5),
                modifier              = Modifier.fillMaxSize(),
                contentPadding        = PaddingValues(16.dp),
                verticalArrangement   = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(items = uiState.items, key = { it.level.id }) { item ->
                    LevelCard(
                        item        = item,
                        lockedLabel = strings.locked,
                        onClick     = {
                            if (item.isUnlocked) {
                                selectedItem = item
                                leaderboardVm.loadForLevel(item.level.id)
                            }
                        }
                    )
                }
            }
        }
    }

    // ── Level detail sheet (Info + Moves ranking + Time ranking) ─────────────
    selectedItem?.let { item ->
        val lbState by leaderboardVm.uiState.collectAsState()

        ModalBottomSheet(
            onDismissRequest = { selectedItem = null },
            containerColor   = Color(0xFF1C1032),
            sheetState       = sheetState,
            dragHandle       = { BottomSheetDefaults.DragHandle(color = Color.White.copy(alpha = 0.3f)) }
        ) {
            // Tab bar: Info | Moves | Time
            val tabLabels = listOf(strings.infoTab, strings.movesTab, strings.timeTab)
            val lbUiState by leaderboardVm.uiState.collectAsState()
            // The selected tab is driven by leaderboardVm; but "Info" (index 0) is local
            var localTab by remember { mutableIntStateOf(0) }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp)
            ) {
                // Header
                Row(
                    modifier      = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text       = strings.levelN(item.level.id),
                        style      = MaterialTheme.typography.titleLarge,
                        color      = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    DifficultyBadge(item.level.difficulty)
                }

                Spacer(Modifier.height(4.dp))

                // Tabs
                RankingTabs(
                    selectedTab   = localTab,
                    onTabSelected = { localTab = it },
                    tabLabels     = tabLabels
                )

                Spacer(Modifier.height(12.dp))

                when (localTab) {
                    // ── Info tab ────────────────────────────────────────────
                    0 -> LevelInfoContent(
                        item      = item,
                        onPlay    = { selectedItem = null; onLevelSelected(item.level.id) }
                    )
                    // ── Moves ranking tab ───────────────────────────────────
                    1 -> {
                        SignInBanner(
                            uiState     = lbUiState,
                            activity    = activity,
                            title       = strings.signInToCompete,
                            desc        = strings.signInDesc,
                            buttonLabel = strings.signInButton,
                            onSignIn    = { /* handled by MainActivity */ }
                        )
                        RankingList(
                            entries    = lbUiState.movesEntries,
                            isLoading  = lbUiState.isLoadingMoves,
                            scoreLabel = strings.movesUnit,
                            noScores   = strings.noScoresYet,
                            rankSymbol = strings.rankSymbol
                        )
                    }
                    // ── Time ranking tab ────────────────────────────────────
                    2 -> {
                        SignInBanner(
                            uiState     = lbUiState,
                            activity    = activity,
                            title       = strings.signInToCompete,
                            desc        = strings.signInDesc,
                            buttonLabel = strings.signInButton,
                            onSignIn    = { /* handled by MainActivity */ }
                        )
                        RankingList(
                            entries    = lbUiState.timeEntries,
                            isLoading  = lbUiState.isLoadingTime,
                            scoreLabel = strings.timeUnit,
                            noScores   = strings.noScoresYet,
                            rankSymbol = strings.rankSymbol
                        )
                    }
                }
            }
        }
    }
}

// ── Info tab content ─────────────────────────────────────────────────────────

@Composable
private fun LevelInfoContent(
    item: LevelItem,
    onPlay: () -> Unit
) {
    val strings     = LocalStrings.current
    val level       = item.level
    val progress    = item.progress
    val isCompleted = progress?.isCompleted == true

    val moves3Stars = level.optimalMoves + 1
    val moves2Stars = (level.maxMoves * 0.70).toInt()
    val earnedStars = progress?.stars ?: 0

    LazyColumn(
        modifier      = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 4.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            // Status + stars
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                StarsDisplay(stars = earnedStars, animated = false)
                StatusBadge(isCompleted, strings.completedStatus, strings.notCompleted)
            }
        }

        if (isCompleted) {
            item {
                HorizontalDivider(color = Color.White.copy(alpha = 0.08f))
                Spacer(Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    StatCard(
                        modifier = Modifier.weight(1f),
                        icon = "🕐", label = strings.bestTime,
                        value = progress?.bestTimeSeconds?.let { s ->
                            "%02d:%02d".format(s / 60, s % 60)
                        } ?: "--"
                    )
                    StatCard(
                        modifier = Modifier.weight(1f),
                        icon = "🎯", label = strings.bestMoves,
                        value = progress?.bestMoves?.toString() ?: "--"
                    )
                }
            }
        }

        item {
            HorizontalDivider(color = Color.White.copy(alpha = 0.08f))
            Spacer(Modifier.height(4.dp))
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(
                    text  = strings.starRequirements.uppercase(),
                    style = MaterialTheme.typography.labelLarge,
                    color = OnSurfaceDim
                )
                StarRequirementRow(3, strings.movesThreshold(moves3Stars), earnedStars >= 3)
                StarRequirementRow(2, strings.movesThreshold(moves2Stars), earnedStars >= 2)
                StarRequirementRow(1, strings.anyCompletion,               earnedStars >= 1)
                Text(
                    text  = strings.maxMovesLabel(level.maxMoves),
                    style = MaterialTheme.typography.bodySmall,
                    color = OnSurfaceDim.copy(alpha = 0.6f)
                )
            }
        }

        item {
            HorizontalDivider(color = Color.White.copy(alpha = 0.08f))
            Spacer(Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick  = { /* sheet handles dismiss */ },
                    modifier = Modifier.weight(1f).height(50.dp),
                    shape    = RoundedCornerShape(12.dp),
                    colors   = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)
                ) { Text(strings.back) }

                Button(
                    onClick  = onPlay,
                    modifier = Modifier.weight(2f).height(50.dp),
                    shape    = RoundedCornerShape(12.dp),
                    colors   = ButtonDefaults.buttonColors(
                        containerColor = if (isCompleted) Color(0xFF00897B) else Primary
                    )
                ) {
                    Icon(
                        imageVector = if (isCompleted) Icons.Default.Replay else Icons.Default.PlayArrow,
                        contentDescription = null, modifier = Modifier.size(20.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        if (isCompleted) strings.replay else strings.play,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

// ── Helper composables ───────────────────────────────────────────────────────

@Composable
private fun DifficultyBadge(difficulty: Difficulty) {
    val strings  = LocalStrings.current
    val diffColor = when (difficulty) {
        Difficulty.EASY   -> Color(0xFF43A047)
        Difficulty.MEDIUM -> Color(0xFFF57C00)
        Difficulty.HARD   -> Color(0xFFE53935)
    }
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(diffColor.copy(alpha = 0.2f))
            .border(1.dp, diffColor.copy(alpha = 0.5f), RoundedCornerShape(20.dp))
            .padding(horizontal = 10.dp, vertical = 3.dp)
    ) {
        Text(strings.difficultyName(difficulty), color = diffColor,
            style = MaterialTheme.typography.labelLarge)
    }
}

@Composable
private fun StatusBadge(isCompleted: Boolean, completedLabel: String, notCompletedLabel: String) {
    val color = if (isCompleted) SuccessGreen else OnSurfaceDim
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Box(Modifier.size(8.dp).clip(CircleShape).background(color))
        Text(if (isCompleted) completedLabel else notCompletedLabel,
            color = color, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
private fun StatCard(modifier: Modifier, icon: String, label: String, value: String) {
    Column(
        modifier            = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White.copy(alpha = 0.05f))
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(icon, fontSize = 22.sp)
        Text(value, style = MaterialTheme.typography.titleLarge,
            color = Color.White, fontWeight = FontWeight.Bold)
        Text(label, style = MaterialTheme.typography.bodySmall, color = OnSurfaceDim)
    }
}

@Composable
private fun StarRequirementRow(filledStars: Int, label: String, achieved: Boolean) {
    val rowColor = if (achieved) SuccessGreen else Color.White.copy(alpha = 0.75f)
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
            repeat(3) { i ->
                Text(
                    if (i < filledStars) "★" else "☆", fontSize = 16.sp,
                    color = if (i < filledStars) StarColor else Color.White.copy(alpha = 0.25f)
                )
            }
        }
        Text(label, style = MaterialTheme.typography.bodyMedium,
            color = rowColor, modifier = Modifier.weight(1f))
        if (achieved) {
            Icon(Icons.Default.CheckCircle, contentDescription = null,
                tint = SuccessGreen, modifier = Modifier.size(16.dp))
        }
    }
}

// ── Level grid card ──────────────────────────────────────────────────────────

@Composable
private fun LevelCard(item: LevelItem, lockedLabel: String, onClick: () -> Unit) {
    val isCompleted = item.progress?.isCompleted == true
    val isUnlocked  = item.isUnlocked
    val stars       = item.progress?.stars ?: 0

    val bgColor = when {
        isCompleted -> Color(0xFF1B5E20).copy(alpha = 0.8f)
        isUnlocked  -> SurfaceCard
        else        -> Color(0xFF1A1A1A)
    }
    val borderColor = when {
        isCompleted -> SuccessGreen.copy(alpha = 0.6f)
        isUnlocked  -> Color.White.copy(alpha = 0.15f)
        else        -> Color.Transparent
    }

    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(bgColor)
            .border(1.dp, borderColor, RoundedCornerShape(10.dp))
            .clickable(enabled = isUnlocked, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        if (!isUnlocked) {
            Icon(Icons.Default.Lock, contentDescription = lockedLabel,
                tint = Color.Gray, modifier = Modifier.size(20.dp))
        } else {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(item.level.id.toString(), fontSize = 16.sp,
                    fontWeight = FontWeight.Bold, color = Color.White,
                    textAlign = TextAlign.Center)
                if (isCompleted) {
                    Row {
                        repeat(3) { i ->
                            Text(if (i < stars) "★" else "☆",
                                fontSize = 8.sp, color = StarColor)
                        }
                    }
                }
            }
        }
    }
}
