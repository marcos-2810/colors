package com.colors.game.presentation.viewmodel;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.colors.game.data.model.*;
import com.colors.game.data.remote.LeaderboardRepository;
import com.colors.game.data.repository.GameStateRepository;
import com.colors.game.data.repository.LevelRepository;
import com.colors.game.data.repository.SettingsRepository;
import com.colors.game.domain.FloodFillEngine;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001a\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BY\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\b\b\u0002\u0010\r\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\u000fJ\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0005H\u00c6\u0003J\u0015\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u000bH\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u000bH\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u000bH\u00c6\u0003J\t\u0010 \u001a\u00020\u000bH\u00c6\u0003J]\u0010!\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000bH\u00c6\u0001J\u0013\u0010\"\u001a\u00020\u000b2\b\u0010#\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010$\u001a\u00020\tH\u00d6\u0001J\t\u0010%\u001a\u00020&H\u00d6\u0001R\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u000e\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\r\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0011\u0010\f\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014\u00a8\u0006\'"}, d2 = {"Lcom/colors/game/presentation/viewmodel/GameUiState;", "", "gameState", "Lcom/colors/game/data/model/GameState;", "settings", "Lcom/colors/game/data/model/AppSettings;", "animatingCells", "", "Lcom/colors/game/data/model/Position;", "", "showPauseMenu", "", "showResultDialog", "showLeaderboard", "isLoading", "(Lcom/colors/game/data/model/GameState;Lcom/colors/game/data/model/AppSettings;Ljava/util/Map;ZZZZ)V", "getAnimatingCells", "()Ljava/util/Map;", "getGameState", "()Lcom/colors/game/data/model/GameState;", "()Z", "getSettings", "()Lcom/colors/game/data/model/AppSettings;", "getShowLeaderboard", "getShowPauseMenu", "getShowResultDialog", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "toString", "", "app_release"})
public final class GameUiState {
    @org.jetbrains.annotations.Nullable()
    private final com.colors.game.data.model.GameState gameState = null;
    @org.jetbrains.annotations.NotNull()
    private final com.colors.game.data.model.AppSettings settings = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Map<com.colors.game.data.model.Position, java.lang.Integer> animatingCells = null;
    private final boolean showPauseMenu = false;
    private final boolean showResultDialog = false;
    private final boolean showLeaderboard = false;
    private final boolean isLoading = false;
    
    public GameUiState(@org.jetbrains.annotations.Nullable()
    com.colors.game.data.model.GameState gameState, @org.jetbrains.annotations.NotNull()
    com.colors.game.data.model.AppSettings settings, @org.jetbrains.annotations.NotNull()
    java.util.Map<com.colors.game.data.model.Position, java.lang.Integer> animatingCells, boolean showPauseMenu, boolean showResultDialog, boolean showLeaderboard, boolean isLoading) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.colors.game.data.model.GameState getGameState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.colors.game.data.model.AppSettings getSettings() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<com.colors.game.data.model.Position, java.lang.Integer> getAnimatingCells() {
        return null;
    }
    
    public final boolean getShowPauseMenu() {
        return false;
    }
    
    public final boolean getShowResultDialog() {
        return false;
    }
    
    public final boolean getShowLeaderboard() {
        return false;
    }
    
    public final boolean isLoading() {
        return false;
    }
    
    public GameUiState() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.colors.game.data.model.GameState component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.colors.game.data.model.AppSettings component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<com.colors.game.data.model.Position, java.lang.Integer> component3() {
        return null;
    }
    
    public final boolean component4() {
        return false;
    }
    
    public final boolean component5() {
        return false;
    }
    
    public final boolean component6() {
        return false;
    }
    
    public final boolean component7() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.colors.game.presentation.viewmodel.GameUiState copy(@org.jetbrains.annotations.Nullable()
    com.colors.game.data.model.GameState gameState, @org.jetbrains.annotations.NotNull()
    com.colors.game.data.model.AppSettings settings, @org.jetbrains.annotations.NotNull()
    java.util.Map<com.colors.game.data.model.Position, java.lang.Integer> animatingCells, boolean showPauseMenu, boolean showResultDialog, boolean showLeaderboard, boolean isLoading) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}