package com.colors.game.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.colors.game.data.model.Difficulty
import com.colors.game.presentation.viewmodel.DailyCalendarEntry
import com.colors.game.presentation.viewmodel.DailyCalendarViewModel
import com.colors.game.ui.LocalStrings
import com.colors.game.ui.theme.*
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun DailyCalendarScreen(
    onNavigateBack: () -> Unit,
    onPlayDate: (String) -> Unit,
    viewModel: DailyCalendarViewModel = hiltViewModel()
) {
    val uiState  = viewModel.uiState.collectAsState().value
    val strings  = LocalStrings.current
    val gradient = Brush.verticalGradient(listOf(Color(0xFF1A0533), Color(0xFF0D1B2A)))

    val today         = LocalDate.now()
    val startYearMonth = YearMonth.of(2026, 4)
    val todayYearMonth = YearMonth.from(today)

    var displayedMonth by remember { mutableStateOf(todayYearMonth) }

    // Map dateKey → entry for quick lookup
    val entryMap = remember(uiState.entries) {
        uiState.entries.associateBy { it.dateKey }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .systemBarsPadding()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            // ── Top bar ──────────────────────────────────────────────
            Row(
                modifier          = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onNavigateBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = strings.back, tint = Color.White)
                }
                Text(
                    text       = strings.dailyCalendar,
                    style      = MaterialTheme.typography.titleLarge,
                    color      = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier   = Modifier.padding(start = 4.dp)
                )
            }

            HorizontalDivider(color = Color.White.copy(alpha = 0.08f))

            if (uiState.isLoading) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = Primary)
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    // ── Month navigation ──────────────────────────────
                    MonthHeader(
                        month       = displayedMonth,
                        canGoPrev   = displayedMonth > startYearMonth,
                        canGoNext   = displayedMonth < todayYearMonth,
                        onPrev      = { displayedMonth = displayedMonth.minusMonths(1) },
                        onNext      = { displayedMonth = displayedMonth.plusMonths(1) }
                    )

                    Spacer(Modifier.height(12.dp))

                    // ── Day-of-week headers ───────────────────────────
                    WeekDayHeaders()

                    Spacer(Modifier.height(4.dp))

                    // ── Calendar grid ─────────────────────────────────
                    CalendarGrid(
                        month      = displayedMonth,
                        today      = today,
                        startDate  = LocalDate.of(2026, 4, 22),
                        entryMap   = entryMap,
                        onDayClick = { date ->
                            val key = date.toString()
                            onPlayDate(key)
                        }
                    )

                    Spacer(Modifier.height(24.dp))

                    // ── Legend ────────────────────────────────────────
                    Legend(strings)
                }
            }
        }
    }
}

// ── Month header with navigation ──────────────────────────────────────────────

@Composable
private fun MonthHeader(
    month: YearMonth,
    canGoPrev: Boolean,
    canGoNext: Boolean,
    onPrev: () -> Unit,
    onNext: () -> Unit
) {
    val monthName = month.month
        .getDisplayName(TextStyle.FULL, Locale.getDefault())
        .replaceFirstChar { it.uppercase() }

    Row(
        modifier              = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment     = Alignment.CenterVertically
    ) {
        IconButton(onClick = onPrev, enabled = canGoPrev) {
            Icon(
                Icons.Default.ChevronLeft,
                contentDescription = null,
                tint = if (canGoPrev) Color.White else Color.White.copy(alpha = 0.2f)
            )
        }

        Text(
            text       = "$monthName ${month.year}",
            style      = MaterialTheme.typography.titleLarge,
            color      = Color.White,
            fontWeight = FontWeight.Bold
        )

        IconButton(onClick = onNext, enabled = canGoNext) {
            Icon(
                Icons.Default.ChevronRight,
                contentDescription = null,
                tint = if (canGoNext) Color.White else Color.White.copy(alpha = 0.2f)
            )
        }
    }
}

// ── Day-of-week header row ────────────────────────────────────────────────────

@Composable
private fun WeekDayHeaders() {
    // Monday first
    val days = listOf(
        DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
        DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY
    )
    Row(modifier = Modifier.fillMaxWidth()) {
        days.forEach { day ->
            Text(
                text      = day.getDisplayName(TextStyle.NARROW, Locale.getDefault()),
                modifier  = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                style     = MaterialTheme.typography.labelMedium,
                color     = OnSurfaceDim,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

// ── Calendar grid ─────────────────────────────────────────────────────────────

@Composable
private fun CalendarGrid(
    month: YearMonth,
    today: LocalDate,
    startDate: LocalDate,
    entryMap: Map<String, DailyCalendarEntry>,
    onDayClick: (LocalDate) -> Unit
) {
    val firstDay     = month.atDay(1)
    val daysInMonth  = month.lengthOfMonth()
    // Monday = 1, so offset = dayOfWeek.value - 1 (Mon=0, Tue=1, ... Sun=6)
    val startOffset  = (firstDay.dayOfWeek.value - 1)

    val totalCells = startOffset + daysInMonth
    val rows       = (totalCells + 6) / 7  // ceiling division

    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        for (row in 0 until rows) {
            Row(
                modifier              = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                for (col in 0 until 7) {
                    val cellIndex = row * 7 + col
                    val dayNumber = cellIndex - startOffset + 1

                    if (dayNumber < 1 || dayNumber > daysInMonth) {
                        // Empty cell
                        Box(modifier = Modifier.weight(1f).aspectRatio(1f))
                    } else {
                        val date      = month.atDay(dayNumber)
                        val dateKey   = date.toString()
                        val entry     = entryMap[dateKey]
                        val isToday   = date == today
                        val isFuture  = date.isAfter(today)
                        val isBeforeStart = date.isBefore(startDate)
                        val isAvailable   = !isFuture && !isBeforeStart

                        DayCell(
                            day         = dayNumber,
                            isToday     = isToday,
                            isAvailable = isAvailable,
                            entry       = entry,
                            onClick     = if (isAvailable) ({ onDayClick(date) }) else null,
                            modifier    = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}

// ── Individual day cell ───────────────────────────────────────────────────────

@Composable
private fun DayCell(
    day: Int,
    isToday: Boolean,
    isAvailable: Boolean,
    entry: DailyCalendarEntry?,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier
) {
    val isCompleted = entry?.record?.isCompleted == true

    val bgColor = when {
        isToday && isCompleted -> SuccessGreen.copy(alpha = 0.25f)
        isToday                -> Primary.copy(alpha = 0.3f)
        isCompleted            -> SuccessGreen.copy(alpha = 0.12f)
        isAvailable            -> Color.White.copy(alpha = 0.05f)
        else                   -> Color.Transparent
    }

    val borderColor = when {
        isToday     -> Primary
        isCompleted -> SuccessGreen.copy(alpha = 0.4f)
        else        -> Color.Transparent
    }

    val textColor = when {
        !isAvailable -> Color.White.copy(alpha = 0.2f)
        isToday      -> Color.White
        isCompleted  -> SuccessGreen
        else         -> Color.White.copy(alpha = 0.75f)
    }

    Box(
        modifier = modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(bgColor)
            .then(
                if (borderColor != Color.Transparent)
                    Modifier.border(1.dp, borderColor, RoundedCornerShape(10.dp))
                else Modifier
            )
            .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text       = "$day",
                style      = MaterialTheme.typography.bodyMedium,
                color      = textColor,
                fontWeight = if (isToday) FontWeight.Bold else FontWeight.Normal,
                textAlign  = TextAlign.Center
            )

            // Completion indicator
            if (isCompleted && entry != null) {
                Spacer(Modifier.height(2.dp))
                StarsRow(stars = entry.record?.stars ?: 0)
            } else if (isAvailable && !isCompleted) {
                Spacer(Modifier.height(3.dp))
                // Small difficulty dot
                entry?.let {
                    Box(
                        modifier = Modifier
                            .size(5.dp)
                            .clip(CircleShape)
                            .background(difficultyColor(it.difficulty).copy(alpha = 0.7f))
                    )
                }
            }
        }
    }
}

@Composable
private fun StarsRow(stars: Int) {
    Row(horizontalArrangement = Arrangement.Center) {
        repeat(3) { i ->
            Text(
                text     = if (i < stars) "★" else "☆",
                fontSize = 7.sp,
                color    = if (i < stars) StarColor else Color.White.copy(alpha = 0.2f),
                lineHeight = 8.sp
            )
        }
    }
}

// ── Legend ────────────────────────────────────────────────────────────────────

@Composable
private fun Legend(strings: com.colors.game.ui.AppStrings) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White.copy(alpha = 0.04f))
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        LegendItem(color = SuccessGreen,         label = strings.dailyCompleted)
        LegendItem(color = Primary,              label = strings.dailyToday)
        LegendItem(color = Color(0xFF4CAF50),    label = strings.easy,   dot = true)
        LegendItem(color = Color(0xFFFF9800),    label = strings.medium, dot = true)
        LegendItem(color = Color(0xFFF44336),    label = strings.hard,   dot = true)
    }
}

@Composable
private fun LegendItem(color: Color, label: String, dot: Boolean = false) {
    Row(
        verticalAlignment     = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (dot) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(color)
            )
        } else {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(color.copy(alpha = 0.3f))
                    .border(1.dp, color.copy(alpha = 0.5f), RoundedCornerShape(4.dp))
            )
        }
        Text(
            text  = label,
            style = MaterialTheme.typography.bodySmall,
            color = Color.White.copy(alpha = 0.7f)
        )
    }
}

// ── Helpers ───────────────────────────────────────────────────────────────────

private fun difficultyColor(difficulty: Difficulty) = when (difficulty) {
    Difficulty.EASY   -> Color(0xFF4CAF50)
    Difficulty.MEDIUM -> Color(0xFFFF9800)
    Difficulty.HARD   -> Color(0xFFF44336)
}
