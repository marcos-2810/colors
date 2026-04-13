package com.colors.game.presentation.screen;

import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import androidx.compose.animation.*;
import androidx.compose.animation.core.*;
import androidx.compose.foundation.layout.*;
import androidx.compose.material.icons.Icons;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import com.colors.game.data.model.GameColor;
import com.colors.game.data.model.Position;
import com.colors.game.domain.LevelGenerator;
import com.colors.game.presentation.component.*;
import com.colors.game.presentation.viewmodel.GameViewModel;
import com.colors.game.ui.theme.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000<\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\t\u001a^\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00052\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\u000eH\u0003\u001aB\u0010\u000f\u001a\u00020\u00012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e2\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u0007\u001a0\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00010\u000eH\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001b\u0010\u001c\u001aR\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u00052\u0006\u0010!\u001a\u00020\u00052\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00010\u000eH\u0003\u001az\u0010%\u001a\u00020\u00012\u0006\u0010&\u001a\u00020\'2\u0006\u0010(\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010)\u001a\u00020\u00052\u0006\u0010*\u001a\u00020\u00052\u0006\u0010+\u001a\u00020\u00052\u0006\u0010,\u001a\u00020\u00052\u0006\u0010-\u001a\u00020\u00052\u0006\u0010.\u001a\u00020\u00052\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00010\u000eH\u0003\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u00060"}, d2 = {"GameHUD", "", "levelId", "", "difficulty", "", "movesRemaining", "maxMoves", "timeElapsed", "movesLabel", "timeLabel", "pauseLabel", "levelLabel", "onPause", "Lkotlin/Function0;", "GameScreen", "onNavigateBack", "onNavigateMenu", "onNavigateNext", "Lkotlin/Function1;", "viewModel", "Lcom/colors/game/presentation/viewmodel/GameViewModel;", "PauseButton", "text", "color", "Landroidx/compose/ui/graphics/Color;", "onClick", "PauseButton-bw27NRU", "(Ljava/lang/String;JLkotlin/jvm/functions/Function0;)V", "PauseMenuOverlay", "title", "resumeBtn", "restartBtn", "menuBtn", "onResume", "onRestart", "onMenu", "ResultDialog", "isWin", "", "stars", "completedStr", "gameOverStr", "noMovesStr", "nextLevelStr", "tryAgainStr", "mainMenuStr", "onNext", "app_debug"})
public final class GameScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void GameScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateBack, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateMenu, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onNavigateNext, @org.jetbrains.annotations.NotNull()
    com.colors.game.presentation.viewmodel.GameViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void GameHUD(int levelId, java.lang.String difficulty, int movesRemaining, int maxMoves, int timeElapsed, java.lang.String movesLabel, java.lang.String timeLabel, java.lang.String pauseLabel, java.lang.String levelLabel, kotlin.jvm.functions.Function0<kotlin.Unit> onPause) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void PauseMenuOverlay(java.lang.String title, java.lang.String resumeBtn, java.lang.String restartBtn, java.lang.String menuBtn, kotlin.jvm.functions.Function0<kotlin.Unit> onResume, kotlin.jvm.functions.Function0<kotlin.Unit> onRestart, kotlin.jvm.functions.Function0<kotlin.Unit> onMenu) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ResultDialog(boolean isWin, int stars, int levelId, java.lang.String completedStr, java.lang.String gameOverStr, java.lang.String noMovesStr, java.lang.String nextLevelStr, java.lang.String tryAgainStr, java.lang.String mainMenuStr, kotlin.jvm.functions.Function0<kotlin.Unit> onRestart, kotlin.jvm.functions.Function0<kotlin.Unit> onMenu, kotlin.jvm.functions.Function0<kotlin.Unit> onNext) {
    }
}