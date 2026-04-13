package com.colors.game.presentation.viewmodel;

import androidx.lifecycle.ViewModel;
import com.colors.game.data.repository.GameStateRepository;
import com.colors.game.data.repository.SettingsRepository;
import com.colors.game.domain.LevelGenerator;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0019\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BE\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0002\u0010\u000bJ\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0005H\u00c6\u0003J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0014J\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0005H\u00c6\u0003J\u0010\u0010\u001d\u001a\u0004\u0018\u00010\nH\u00c6\u0003\u00a2\u0006\u0002\u0010\rJN\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nH\u00c6\u0001\u00a2\u0006\u0002\u0010\u001fJ\u0013\u0010 \u001a\u00020\u00052\b\u0010!\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\"\u001a\u00020\u0003H\u00d6\u0001J\t\u0010#\u001a\u00020$H\u00d6\u0001R\u0015\u0010\t\u001a\u0004\u0018\u00010\n\u00a2\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0011\u0010\b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0010\u00a8\u0006%"}, d2 = {"Lcom/colors/game/presentation/viewmodel/MainMenuUiState;", "", "nextLevelId", "", "hasNeverPlayed", "", "resumableLevelId", "totalCompleted", "tutorialCompleted", "activeGameCoverage", "", "(IZLjava/lang/Integer;IZLjava/lang/Float;)V", "getActiveGameCoverage", "()Ljava/lang/Float;", "Ljava/lang/Float;", "getHasNeverPlayed", "()Z", "getNextLevelId", "()I", "getResumableLevelId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getTotalCompleted", "getTutorialCompleted", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(IZLjava/lang/Integer;IZLjava/lang/Float;)Lcom/colors/game/presentation/viewmodel/MainMenuUiState;", "equals", "other", "hashCode", "toString", "", "app_debug"})
public final class MainMenuUiState {
    private final int nextLevelId = 0;
    private final boolean hasNeverPlayed = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer resumableLevelId = null;
    private final int totalCompleted = 0;
    private final boolean tutorialCompleted = false;
    
    /**
     * 0..1 fraction of board covered in the active game for [nextLevelId]. Null if no game in progress.
     */
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Float activeGameCoverage = null;
    
    public MainMenuUiState(int nextLevelId, boolean hasNeverPlayed, @org.jetbrains.annotations.Nullable()
    java.lang.Integer resumableLevelId, int totalCompleted, boolean tutorialCompleted, @org.jetbrains.annotations.Nullable()
    java.lang.Float activeGameCoverage) {
        super();
    }
    
    public final int getNextLevelId() {
        return 0;
    }
    
    public final boolean getHasNeverPlayed() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getResumableLevelId() {
        return null;
    }
    
    public final int getTotalCompleted() {
        return 0;
    }
    
    public final boolean getTutorialCompleted() {
        return false;
    }
    
    /**
     * 0..1 fraction of board covered in the active game for [nextLevelId]. Null if no game in progress.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Float getActiveGameCoverage() {
        return null;
    }
    
    public MainMenuUiState() {
        super();
    }
    
    public final int component1() {
        return 0;
    }
    
    public final boolean component2() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component3() {
        return null;
    }
    
    public final int component4() {
        return 0;
    }
    
    public final boolean component5() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Float component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.colors.game.presentation.viewmodel.MainMenuUiState copy(int nextLevelId, boolean hasNeverPlayed, @org.jetbrains.annotations.Nullable()
    java.lang.Integer resumableLevelId, int totalCompleted, boolean tutorialCompleted, @org.jetbrains.annotations.Nullable()
    java.lang.Float activeGameCoverage) {
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