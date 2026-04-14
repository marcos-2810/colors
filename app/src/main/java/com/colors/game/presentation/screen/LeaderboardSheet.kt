package com.colors.game.presentation.screen

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.colors.game.data.model.LeaderboardEntry
import com.colors.game.data.model.LeaderboardType
import com.colors.game.presentation.viewmodel.LeaderboardUiState
import com.colors.game.presentation.viewmodel.LeaderboardViewModel
import com.colors.game.ui.LocalStrings
import com.colors.game.ui.theme.*

// ── Entry point: standalone bottom sheet (Game completion) ──────────────────

/**
 * Shows the leaderboard for [levelId] as a [ModalBottomSheet].
 * Contains two tabs: Moves ranking | Time ranking.
 * Used on game completion (no "Info" tab needed — the result dialog covers that).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeaderboardSheet(
    levelId: Int,
    viewModel: LeaderboardViewModel,
    onDismiss: () -> Unit
) {
    val strings  = LocalStrings.current
    val uiState  by viewModel.uiState.collectAsState()
    val activity = LocalContext.current as? Activity

    LaunchedEffect(levelId) { viewModel.loadForLevel(levelId) }

    ModalBottomSheet(
        onDismissRequest  = onDismiss,
        containerColor    = Color(0xFF1C1032),
        dragHandle        = { BottomSheetDefaults.DragHandle(color = Color.White.copy(alpha = 0.3f)) },
        sheetState        = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp)) {
            // Header
            Text(
                text       = "${strings.leaderboard} · ${strings.levelN(levelId)}",
                style      = MaterialTheme.typography.titleLarge,
                color      = Color.White,
                fontWeight = FontWeight.Bold,
                modifier   = Modifier.padding(horizontal = 20.dp, vertical = 4.dp)
            )

            Spacer(Modifier.height(8.dp))

            RankingTabs(
                selectedTab = uiState.selectedTab,
                onTabSelected = viewModel::selectTab,
                tabLabels = listOf(strings.movesTab, strings.timeTab)
            )

            Spacer(Modifier.height(12.dp))

            SignInBanner(uiState, activity, strings.signInToCompete, strings.signInDesc, strings.signInButton) {
                activity?.let { viewModel.let { _ -> /* sign-in handled below */ } }
                activity?.let { act ->
                    // Access PlayGamesManager via the context — handled through the ViewModel
                }
            }

            when (uiState.selectedTab) {
                0 -> RankingList(
                    entries    = uiState.movesEntries,
                    isLoading  = uiState.isLoadingMoves,
                    scoreLabel = strings.movesUnit,
                    noScores   = strings.noScoresYet,
                    rankSymbol = strings.rankSymbol
                )
                1 -> RankingList(
                    entries    = uiState.timeEntries,
                    isLoading  = uiState.isLoadingTime,
                    scoreLabel = strings.timeUnit,
                    noScores   = strings.noScoresYet,
                    rankSymbol = strings.rankSymbol
                )
            }
        }
    }
}

// ── Reusable ranking components (also used inside LevelDetailSheet) ──────────

@Composable
internal fun RankingTabs(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit,
    tabLabels: List<String>
) {
    TabRow(
        selectedTabIndex = selectedTab,
        containerColor   = Color.Transparent,
        contentColor     = Primary
    ) {
        tabLabels.forEachIndexed { i, label ->
            Tab(
                selected      = selectedTab == i,
                onClick       = { onTabSelected(i) },
                text          = {
                    Text(
                        text       = label,
                        color      = if (selectedTab == i) Primary else OnSurfaceDim,
                        fontWeight = if (selectedTab == i) FontWeight.Bold else FontWeight.Normal
                    )
                }
            )
        }
    }
}

@Composable
internal fun SignInBanner(
    uiState: LeaderboardUiState,
    activity: Activity?,
    title: String,
    desc: String,
    buttonLabel: String,
    onSignIn: () -> Unit
) {
    if (!uiState.isSignedIn) {
        Row(
            modifier            = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Primary.copy(alpha = 0.1f))
                .padding(12.dp),
            verticalAlignment   = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(Icons.Default.EmojiEvents, contentDescription = null,
                tint = StarColor, modifier = Modifier.size(28.dp))
            Column(Modifier.weight(1f)) {
                Text(title, style = MaterialTheme.typography.titleSmall,
                    color = Color.White, fontWeight = FontWeight.SemiBold)
                Text(desc, style = MaterialTheme.typography.bodySmall,
                    color = OnSurfaceDim)
            }
            TextButton(onClick = onSignIn) {
                Text(buttonLabel, color = Primary, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
internal fun RankingList(
    entries: List<LeaderboardEntry>,
    isLoading: Boolean,
    scoreLabel: (Int) -> String,
    noScores: String,
    rankSymbol: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier            = modifier
            .fillMaxWidth()
            .heightIn(min = 200.dp, max = 420.dp),
        contentAlignment    = Alignment.TopCenter
    ) {
        when {
            isLoading -> {
                CircularProgressIndicator(
                    color    = Primary,
                    modifier = Modifier.padding(top = 48.dp)
                )
            }
            entries.isEmpty() -> {
                Column(
                    modifier            = Modifier.padding(top = 48.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("🏆", fontSize = 40.sp)
                    Text(noScores, color = OnSurfaceDim,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center)
                }
            }
            else -> {
                // Header row
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(rankSymbol, color = OnSurfaceDim,
                            style = MaterialTheme.typography.labelLarge,
                            modifier = Modifier.width(36.dp))
                        Text("Player", color = OnSurfaceDim,
                            style = MaterialTheme.typography.labelLarge,
                            modifier = Modifier.weight(1f))
                        Text("Score", color = OnSurfaceDim,
                            style = MaterialTheme.typography.labelLarge)
                    }
                    HorizontalDivider(color = Color.White.copy(alpha = 0.08f),
                        modifier = Modifier.padding(horizontal = 16.dp))

                    LazyColumn {
                        itemsIndexed(entries, key = { _, e -> e.playerId }) { _, entry ->
                            RankingRow(entry = entry, scoreLabel = scoreLabel)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun RankingRow(
    entry: LeaderboardEntry,
    scoreLabel: (Int) -> String
) {
    val highlight = entry.isCurrentPlayer
    val rowBg     = if (highlight) Primary.copy(alpha = 0.15f) else Color.Transparent
    val textColor = if (highlight) Color.White else Color.White.copy(alpha = 0.85f)

    Row(
        modifier          = Modifier
            .fillMaxWidth()
            .background(rowBg)
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Rank number / medal
        Box(
            modifier         = Modifier.width(36.dp),
            contentAlignment = Alignment.Center
        ) {
            when (entry.rank) {
                1 -> Text("🥇", fontSize = 20.sp)
                2 -> Text("🥈", fontSize = 20.sp)
                3 -> Text("🥉", fontSize = 20.sp)
                else -> Text(
                    text  = entry.rank.toString(),
                    color = OnSurfaceDim,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Avatar circle with initial
        Box(
            modifier         = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(if (highlight) Primary else Color.White.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text  = entry.playerName.firstOrNull()?.uppercaseChar()?.toString() ?: "?",
                color = Color.White,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold
            )
        }

        // Player name
        Text(
            text     = entry.playerName + if (highlight) " ★" else "",
            color    = textColor,
            style    = MaterialTheme.typography.bodyMedium,
            fontWeight = if (highlight) FontWeight.Bold else FontWeight.Normal,
            modifier = Modifier.weight(1f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        // Score
        Text(
            text       = scoreLabel(entry.score),
            color      = if (highlight) Primary else textColor,
            style      = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
    }
    HorizontalDivider(
        color    = Color.White.copy(alpha = 0.05f),
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}
