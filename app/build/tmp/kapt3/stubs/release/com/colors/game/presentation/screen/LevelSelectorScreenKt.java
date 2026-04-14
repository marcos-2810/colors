package com.colors.game.presentation.screen;

import android.app.Activity;
import androidx.compose.foundation.layout.*;
import androidx.compose.foundation.lazy.grid.*;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.*;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import com.colors.game.data.model.Difficulty;
import com.colors.game.presentation.viewmodel.LevelItem;
import com.colors.game.presentation.viewmodel.LevelSelectorViewModel;
import com.colors.game.presentation.viewmodel.LeaderboardViewModel;
import com.colors.game.ui.theme.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000N\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0003\u001a&\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\nH\u0003\u001a\u001e\u0010\u000b\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\nH\u0003\u001a>\u0010\r\u001a\u00020\u00012\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00010\u000f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00010\n2\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u0007\u001a \u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u001aH\u0003\u001a(\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\bH\u0003\u001a \u0010 \u001a\u00020\u00012\u0006\u0010!\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020\b2\u0006\u0010#\u001a\u00020\bH\u0003\u00a8\u0006$"}, d2 = {"DifficultyBadge", "", "difficulty", "Lcom/colors/game/data/model/Difficulty;", "LevelCard", "item", "Lcom/colors/game/presentation/viewmodel/LevelItem;", "lockedLabel", "", "onClick", "Lkotlin/Function0;", "LevelInfoContent", "onPlay", "LevelSelectorScreen", "onLevelSelected", "Lkotlin/Function1;", "", "onBack", "viewModel", "Lcom/colors/game/presentation/viewmodel/LevelSelectorViewModel;", "leaderboardVm", "Lcom/colors/game/presentation/viewmodel/LeaderboardViewModel;", "StarRequirementRow", "filledStars", "label", "achieved", "", "StatCard", "modifier", "Landroidx/compose/ui/Modifier;", "icon", "value", "StatusBadge", "isCompleted", "completedLabel", "notCompletedLabel", "app_release"})
public final class LevelSelectorScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void LevelSelectorScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onLevelSelected, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBack, @org.jetbrains.annotations.NotNull()
    com.colors.game.presentation.viewmodel.LevelSelectorViewModel viewModel, @org.jetbrains.annotations.NotNull()
    com.colors.game.presentation.viewmodel.LeaderboardViewModel leaderboardVm) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void LevelInfoContent(com.colors.game.presentation.viewmodel.LevelItem item, kotlin.jvm.functions.Function0<kotlin.Unit> onPlay) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void DifficultyBadge(com.colors.game.data.model.Difficulty difficulty) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void StatusBadge(boolean isCompleted, java.lang.String completedLabel, java.lang.String notCompletedLabel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void StatCard(androidx.compose.ui.Modifier modifier, java.lang.String icon, java.lang.String label, java.lang.String value) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void StarRequirementRow(int filledStars, java.lang.String label, boolean achieved) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void LevelCard(com.colors.game.presentation.viewmodel.LevelItem item, java.lang.String lockedLabel, kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
}