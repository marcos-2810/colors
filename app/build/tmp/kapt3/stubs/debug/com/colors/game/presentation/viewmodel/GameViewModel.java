package com.colors.game.presentation.viewmodel;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.colors.game.data.model.*;
import com.colors.game.data.repository.GameStateRepository;
import com.colors.game.data.repository.LevelRepository;
import com.colors.game.data.repository.SettingsRepository;
import com.colors.game.domain.FloodFillEngine;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u0016\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u001cH\u0082@\u00a2\u0006\u0002\u0010\u001dJ\u0010\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u000fH\u0002J\b\u0010 \u001a\u00020\u0017H\u0002J\b\u0010!\u001a\u00020\u0017H\u0002J\u0006\u0010\"\u001a\u00020\u0017J\b\u0010#\u001a\u00020\u0017H\u0014J\u0006\u0010$\u001a\u00020\u0017J\u0016\u0010%\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u001cH\u0082@\u00a2\u0006\u0002\u0010\u001dJ\u0006\u0010&\u001a\u00020\u0017J,\u0010\'\u001a\b\u0012\u0004\u0012\u00020)0(2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00190+2\u0006\u0010,\u001a\u00020\u000f2\u0006\u0010-\u001a\u00020\u000fH\u0002J\b\u0010.\u001a\u00020\u0017H\u0002J\b\u0010/\u001a\u00020\u0017H\u0002J\u0006\u00100\u001a\u00020\u0017R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\r0\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015\u00a8\u00061"}, d2 = {"Lcom/colors/game/presentation/viewmodel/GameViewModel;", "Landroidx/lifecycle/ViewModel;", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "levelRepo", "Lcom/colors/game/data/repository/LevelRepository;", "gameStateRepo", "Lcom/colors/game/data/repository/GameStateRepository;", "settingsRepo", "Lcom/colors/game/data/repository/SettingsRepository;", "(Landroidx/lifecycle/SavedStateHandle;Lcom/colors/game/data/repository/LevelRepository;Lcom/colors/game/data/repository/GameStateRepository;Lcom/colors/game/data/repository/SettingsRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/colors/game/presentation/viewmodel/GameUiState;", "levelId", "", "timerJob", "Lkotlinx/coroutines/Job;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "applyColor", "", "newColor", "Lcom/colors/game/data/model/GameColor;", "autoSave", "state", "Lcom/colors/game/data/model/GameState;", "(Lcom/colors/game/data/model/GameState;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "buildFreshGameState", "id", "loadGame", "observeSettings", "onBackground", "onCleared", "onForeground", "recordResult", "restartLevel", "selectTopLeft", "", "Lcom/colors/game/data/model/Position;", "cells", "", "rows", "cols", "startTimer", "stopTimer", "togglePause", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class GameViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.colors.game.data.repository.LevelRepository levelRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.colors.game.data.repository.GameStateRepository gameStateRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.colors.game.data.repository.SettingsRepository settingsRepo = null;
    private final int levelId = 0;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.colors.game.presentation.viewmodel.GameUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.colors.game.presentation.viewmodel.GameUiState> uiState = null;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job timerJob;
    
    @javax.inject.Inject()
    public GameViewModel(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.SavedStateHandle savedStateHandle, @org.jetbrains.annotations.NotNull()
    com.colors.game.data.repository.LevelRepository levelRepo, @org.jetbrains.annotations.NotNull()
    com.colors.game.data.repository.GameStateRepository gameStateRepo, @org.jetbrains.annotations.NotNull()
    com.colors.game.data.repository.SettingsRepository settingsRepo) {
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
    
    private final java.lang.Object recordResult(com.colors.game.data.model.GameState state, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}