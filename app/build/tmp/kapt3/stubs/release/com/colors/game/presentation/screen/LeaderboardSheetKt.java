package com.colors.game.presentation.screen;

import android.app.Activity;
import androidx.compose.foundation.layout.*;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.*;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextOverflow;
import com.colors.game.data.model.LeaderboardEntry;
import com.colors.game.data.model.LeaderboardType;
import com.colors.game.presentation.viewmodel.LeaderboardUiState;
import com.colors.game.presentation.viewmodel.LeaderboardViewModel;
import com.colors.game.ui.theme.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000N\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a&\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0007\u001aL\u0010\b\u001a\u00020\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00102\b\b\u0002\u0010\u0013\u001a\u00020\u0014H\u0001\u001a$\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u000b2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00100\u000fH\u0003\u001a2\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u00032\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u000f2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00100\nH\u0001\u001a@\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010 \u001a\u00020\u00102\u0006\u0010!\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020\u00102\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0001\u00a8\u0006$"}, d2 = {"LeaderboardSheet", "", "levelId", "", "viewModel", "Lcom/colors/game/presentation/viewmodel/LeaderboardViewModel;", "onDismiss", "Lkotlin/Function0;", "RankingList", "entries", "", "Lcom/colors/game/data/model/LeaderboardEntry;", "isLoading", "", "scoreLabel", "Lkotlin/Function1;", "", "noScores", "rankSymbol", "modifier", "Landroidx/compose/ui/Modifier;", "RankingRow", "entry", "RankingTabs", "selectedTab", "onTabSelected", "tabLabels", "SignInBanner", "uiState", "Lcom/colors/game/presentation/viewmodel/LeaderboardUiState;", "activity", "Landroid/app/Activity;", "title", "desc", "buttonLabel", "onSignIn", "app_release"})
public final class LeaderboardSheetKt {
    
    /**
     * Shows the leaderboard for [levelId] as a [ModalBottomSheet].
     * Contains two tabs: Moves ranking | Time ranking.
     * Used on game completion (no "Info" tab needed — the result dialog covers that).
     */
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void LeaderboardSheet(int levelId, @org.jetbrains.annotations.NotNull()
    com.colors.game.presentation.viewmodel.LeaderboardViewModel viewModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void RankingTabs(int selectedTab, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onTabSelected, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> tabLabels) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void SignInBanner(@org.jetbrains.annotations.NotNull()
    com.colors.game.presentation.viewmodel.LeaderboardUiState uiState, @org.jetbrains.annotations.Nullable()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String desc, @org.jetbrains.annotations.NotNull()
    java.lang.String buttonLabel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSignIn) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void RankingList(@org.jetbrains.annotations.NotNull()
    java.util.List<com.colors.game.data.model.LeaderboardEntry> entries, boolean isLoading, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, java.lang.String> scoreLabel, @org.jetbrains.annotations.NotNull()
    java.lang.String noScores, @org.jetbrains.annotations.NotNull()
    java.lang.String rankSymbol, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void RankingRow(com.colors.game.data.model.LeaderboardEntry entry, kotlin.jvm.functions.Function1<? super java.lang.Integer, java.lang.String> scoreLabel) {
    }
}