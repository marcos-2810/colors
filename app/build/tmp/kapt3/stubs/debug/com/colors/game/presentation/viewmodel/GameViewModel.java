package com.colors.game.presentation.viewmodel;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.colors.game.data.model.*;
import com.colors.game.data.remote.AdManager;
import com.colors.game.data.remote.LeaderboardRepository;
import com.colors.game.data.repository.GameStateRepository;
import com.colors.game.data.repository.LevelRepository;
import com.colors.game.data.repository.SettingsRepository;
import com.colors.game.domain.FloodFillEngine;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B7\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ\u000e\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fJ\u0016\u0010 \u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\"H\u0082@\u00a2\u0006\u0002\u0010#J\u0010\u0010$\u001a\u00020\"2\u0006\u0010%\u001a\u00020\u0013H\u0002J\u0006\u0010&\u001a\u00020\u001dJ\b\u0010\'\u001a\u00020\u001dH\u0002J\b\u0010(\u001a\u00020\u001dH\u0002J\u0006\u0010)\u001a\u00020\u001dJ\b\u0010*\u001a\u00020\u001dH\u0014J\u0006\u0010+\u001a\u00020\u001dJ\u0016\u0010,\u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\"H\u0082@\u00a2\u0006\u0002\u0010#J\u000e\u0010-\u001a\u00020\u00152\u0006\u0010.\u001a\u00020/J\u0006\u00100\u001a\u00020\u001dJ,\u00101\u001a\b\u0012\u0004\u0012\u000203022\f\u00104\u001a\b\u0012\u0004\u0012\u00020\u001f052\u0006\u00106\u001a\u00020\u00132\u0006\u00107\u001a\u00020\u0013H\u0002J\u0006\u00108\u001a\u00020\u001dJ\b\u00109\u001a\u00020\u001dH\u0002J\b\u0010:\u001a\u00020\u001dH\u0002J\u0006\u0010;\u001a\u00020\u001dR\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00110\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b\u00a8\u0006<"}, d2 = {"Lcom/colors/game/presentation/viewmodel/GameViewModel;", "Landroidx/lifecycle/ViewModel;", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "levelRepo", "Lcom/colors/game/data/repository/LevelRepository;", "gameStateRepo", "Lcom/colors/game/data/repository/GameStateRepository;", "settingsRepo", "Lcom/colors/game/data/repository/SettingsRepository;", "leaderboardRepo", "Lcom/colors/game/data/remote/LeaderboardRepository;", "adManager", "Lcom/colors/game/data/remote/AdManager;", "(Landroidx/lifecycle/SavedStateHandle;Lcom/colors/game/data/repository/LevelRepository;Lcom/colors/game/data/repository/GameStateRepository;Lcom/colors/game/data/repository/SettingsRepository;Lcom/colors/game/data/remote/LeaderboardRepository;Lcom/colors/game/data/remote/AdManager;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/colors/game/presentation/viewmodel/GameUiState;", "levelId", "", "timeLimitAdShown", "", "timerJob", "Lkotlinx/coroutines/Job;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "applyColor", "", "newColor", "Lcom/colors/game/data/model/GameColor;", "autoSave", "state", "Lcom/colors/game/data/model/GameState;", "(Lcom/colors/game/data/model/GameState;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "buildFreshGameState", "id", "dismissLeaderboard", "loadGame", "observeSettings", "onBackground", "onCleared", "onForeground", "recordResult", "requestAd", "activity", "Landroid/app/Activity;", "restartLevel", "selectTopLeft", "", "Lcom/colors/game/data/model/Position;", "cells", "", "rows", "cols", "showLeaderboard", "startTimer", "stopTimer", "togglePause", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class GameViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.colors.game.data.repository.LevelRepository levelRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.colors.game.data.repository.GameStateRepository gameStateRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.colors.game.data.repository.SettingsRepository settingsRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.colors.game.data.remote.LeaderboardRepository leaderboardRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.colors.game.data.remote.AdManager adManager = null;
    private final int levelId = 0;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.colors.game.presentation.viewmodel.GameUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.colors.game.presentation.viewmodel.GameUiState> uiState = null;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job timerJob;
    
    /**
     * Prevents the time-limit ad from firing more than once per level session.
     */
    private boolean timeLimitAdShown = false;
    
    @javax.inject.Inject()
    public GameViewModel(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.SavedStateHandle savedStateHandle, @org.jetbrains.annotations.NotNull()
    com.colors.game.data.repository.LevelRepository levelRepo, @org.jetbrains.annotations.NotNull()
    com.colors.game.data.repository.GameStateRepository gameStateRepo, @org.jetbrains.annotations.NotNull()
    com.colors.game.data.repository.SettingsRepository settingsRepo, @org.jetbrains.annotations.NotNull()
    com.colors.game.data.remote.LeaderboardRepository leaderboardRepo, @org.jetbrains.annotations.NotNull()
    com.colors.game.data.remote.AdManager adManager) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.colors.game.presentation.viewmodel.GameUiState> getUiState() {
        return null;
    }
    
    private final void loadGame() {
    }
    
    private final com.colors.game.data.model.GameState buildFreshGameState(int id) {
        return null;
    }
    
    private final void observeSettings() {
    }
    
    private final void startTimer() {
    }
    
    private final void stopTimer() {
    }
    
    /**
     * Selecciona el grupo conectado a (0,0). Solo uso interno.
     * La celda de inicio es siempre la esquina superior izquierda.
     */
    private final java.util.Set<com.colors.game.data.model.Position> selectTopLeft(java.util.List<? extends com.colors.game.data.model.GameColor> cells, int rows, int cols) {
        return null;
    }
    
    /**
     * Called when the player picks a color from the color picker.
     * El grupo activo es siempre el conectado a (0,0).
     */
    public final void applyColor(@org.jetbrains.annotations.NotNull()
    com.colors.game.data.model.GameColor newColor) {
    }
    
    public final void togglePause() {
    }
    
    public final void restartLevel() {
    }
    
    /**
     * Called from the screen's DisposableEffect / onStop lifecycle
     */
    public final void onBackground() {
    }
    
    public final void onForeground() {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    private final java.lang.Object autoSave(com.colors.game.data.model.GameState state, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    public final void showLeaderboard() {
    }
    
    public final void dismissLeaderboard() {
    }
    
    /**
     * Called by the Screen to actually show the interstitial via AdManager.
     * Returns true if the ad was shown (false = not loaded yet, proceed immediately).
     */
    public final boolean requestAd(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
        return false;
    }
    
    private final java.lang.Object recordResult(com.colors.game.data.model.GameState state, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}