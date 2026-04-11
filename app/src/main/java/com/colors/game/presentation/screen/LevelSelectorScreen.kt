package com.colors.game.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.colors.game.data.model.Difficulty
import com.colors.game.presentation.component.StarsDisplay
import com.colors.game.presentation.viewmodel.LevelItem
import com.colors.game.presentation.viewmodel.LevelSelectorViewModel
import com.colors.game.ui.theme.*

@Composable
fun LevelSelectorScreen(
    onLevelSelected: (Int) -> Unit,
    onBack: () -> Unit,
    viewModel: LevelSelectorViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .systemBarsPadding()
    ) {
        // Top bar
        Row(
            modifier            = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment   = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Atrás", tint = Color.White)
            }
            Text(
                text  = "Seleccionar Nivel",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                modifier = Modifier.weight(1f).padding(start = 4.dp)
            )
        }

        // Difficulty tabs
        Row(
            modifier                = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            horizontalArrangement  = Arrangement.spacedBy(8.dp)
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
                    label    = { Text(diff.displayName) },
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
                CircularProgressIndicator()
            }
        } else {
            LazyVerticalGrid(
                columns           = GridCells.Fixed(5),
                modifier          = Modifier.fillMaxSize(),
                contentPadding    = PaddingValues(16.dp),
                verticalArrangement   = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(
                    items = uiState.items,
                    key   = { it.level.id }
                ) { item ->
                    LevelCard(
                        item     = item,
                        onClick  = { if (item.isUnlocked) onLevelSelected(item.level.id) }
                    )
                }
            }
        }
    }
}

@Composable
private fun LevelCard(
    item: LevelItem,
    onClick: () -> Unit
) {
    val isCompleted = item.progress?.isCompleted == true
    val isUnlocked  = item.isUnlocked
    val stars       = item.progress?.stars ?: 0

    val bgColor = when {
        isCompleted -> Color(0xFF1B5E20).copy(alpha = 0.8f)
        isUnlocked  -> SurfaceCard
        else         -> Color(0xFF1A1A1A)
    }
    val borderColor = when {
        isCompleted -> SuccessGreen.copy(alpha = 0.6f)
        isUnlocked  -> Color.White.copy(alpha = 0.15f)
        else         -> Color.Transparent
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
            Icon(Icons.Default.Lock, contentDescription = "Bloqueado",
                tint = Color.Gray, modifier = Modifier.size(20.dp))
        } else {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text       = item.level.id.toString(),
                    fontSize   = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color      = Color.White,
                    textAlign  = TextAlign.Center
                )
                if (isCompleted) {
                    Row {
                        repeat(3) { i ->
                            Text(
                                text     = if (i < stars) "★" else "☆",
                                fontSize = 8.sp,
                                color    = StarColor
                            )
                        }
                    }
                }
            }
        }
    }
}
