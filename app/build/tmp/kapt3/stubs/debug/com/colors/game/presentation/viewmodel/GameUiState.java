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

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001Bm\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\b\b\u0002\u0010\r\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\u0002\u0010\u0012J\u000b\u0010 \u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010!\u001a\u00020\u0005H\u00c6\u0003J\u0015\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007H\u00c6\u0003J\t\u0010#\u001a\u00020\u000bH\u00c6\u0003J\t\u0010$\u001a\u00020\u000bH\u00c6\u0003J\t\u0010%\u001a\u00020\u000bH\u00c6\u0003J\t\u0010&\u001a\u00020\u000bH\u00c6\u0003J\t\u0010\'\u001a\u00020\u000bH\u00c6\u0003J\t\u0010(\u001a\u00020\u0011H\u00c6\u0003Jq\u0010)\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\u000b2\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u00c6\u0001J\u0013\u0010*\u001a\u00020\u000b2\b\u0010+\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010,\u001a\u00020\tH\u00d6\u0001J\t\u0010-\u001a\u00020.H\u00d6\u0001R\u0011\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u000e\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0019R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u000f\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0019R\u0011\u0010\r\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0019R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0019R\u0011\u0010\f\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0019\u00a8\u0006/"}, d2 = {"Lcom/colors/game/presentation/viewmodel/GameUiState;", "", "gameState", "Lcom/colors/game/data/model/GameState;", "settings", "Lcom/colors/game/data/model/AppSettings;", "animatingCells", "", "Lcom/colors/game/data/model/Position;", "", "showPauseMenu", "", "showResultDialog", "showLeaderboard", "isLoading", "showAd", "adTrigger", "Lcom/colors/game/presentation/viewmodel/AdTrigger;", "(Lcom/colors/game/data/model/GameState;Lcom/colors/game/data/model/AppSettings;Ljava/util/Map;ZZZZZLcom/colors/game/presentation/viewmodel/AdTrigger;)V", "getAdTrigger", "()Lcom/colors/game/presentation/viewmodel/AdTrigger;", "getAnimatingCells", "()Ljava/util/Map;", "getGameState", "()Lcom/colors/game/data/model/GameState;", "()Z", "getSettings", "()Lcom/colors/game/data/model/AppSettings;", "getShowAd", "getShowLeaderboard", "getShowPauseMenu", "getShowResultDialog", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "", "app_debug"})
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
    
    /**
     * Set to true by the ViewModel when an interstitial should be shown.
     */
    private final boolean showAd = false;
    
    /**
     * Identifies what should happen after the ad is dismissed.
     */
    @org.jetbrains.annotations.NotNull()
    private final com.colors.game.presentation.viewmodel.AdTrigger adTrigger = null;
    
    public GameUiState(@org.jetbrains.annotations.Nullable()
    com.colors.game.data.model.GameState gameState, @org.jetbrains.annotations.NotNull()
    com.colors.game.data.model.AppSettings settings, @org.jetbrains.annotations.NotNull()
    java.util.Map<com.colors.game.data.model.Position, java.lang.Integer> animatingCells, boolean showPauseMenu, boolean showResultDialog, boolean showLeaderboard, boolean isLoading, boolean showAd, @org.jetbrains.annotations.NotNull()
    com.colors.game.presentation.viewmodel.AdTrigger adTrigger) {
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
    
    /**
     * Set to true by the ViewModel when an interstitial should be shown.
     */
    public final boolean getShowAd() {
        return false;
    }
    
    /**
     * Identifies what should happen after the ad is dismissed.
     */
    @org.jetbrains.annotations.NotNull()
    public final com.colors.game.presentation.viewmodel.AdTrigger getAdTrigger() {
        return null;
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
    
    public final boolean component8() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.colors.game.presentation.viewmodel.AdTrigger component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.colors.game.presentation.viewmodel.GameUiState copy(@org.jetbrains.annotations.Nullable()
    com.colors.game.data.model.GameState gameState, @org.jetbrains.annotations.NotNull()
    com.colors.game.data.model.AppSettings settings, @org.jetbrains.annotations.NotNull()
    java.util.Map<com.colors.game.data.model.Position, java.lang.Integer> animatingCells, boolean showPauseMenu, boolean showResultDialog, boolean showLeaderboard, boolean isLoading, boolean showAd, @org.jetbrains.annotations.NotNull()
    com.colors.game.presentation.viewmodel.AdTrigger adTrigger) {
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