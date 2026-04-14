package com.colors.game.presentation.viewmodel;

import androidx.lifecycle.ViewModel;
import com.colors.game.data.model.Difficulty;
import com.colors.game.data.model.Level;
import com.colors.game.data.model.LevelProgress;
import com.colors.game.data.repository.GameStateRepository;
import com.colors.game.data.repository.LevelRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0007H\u00c6\u0003J)\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0012\u001a\u00020\u00072\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0018"}, d2 = {"Lcom/colors/game/presentation/viewmodel/LevelItem;", "", "level", "Lcom/colors/game/data/model/Level;", "progress", "Lcom/colors/game/data/model/LevelProgress;", "isUnlocked", "", "(Lcom/colors/game/data/model/Level;Lcom/colors/game/data/model/LevelProgress;Z)V", "()Z", "getLevel", "()Lcom/colors/game/data/model/Level;", "getProgress", "()Lcom/colors/game/data/model/LevelProgress;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "app_release"})
public final class LevelItem {
    @org.jetbrains.annotations.NotNull()
    private final com.colors.game.data.model.Level level = null;
    @org.jetbrains.annotations.Nullable()
    private final com.colors.game.data.model.LevelProgress progress = null;
    private final boolean isUnlocked = false;
    
    public LevelItem(@org.jetbrains.annotations.NotNull()
    com.colors.game.data.model.Level level, @org.jetbrains.annotations.Nullable()
    com.colors.game.data.model.LevelProgress progress, boolean isUnlocked) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.colors.game.data.model.Level getLevel() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.colors.game.data.model.LevelProgress getProgress() {
        return null;
    }
    
    public final boolean isUnlocked() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.colors.game.data.model.Level component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.colors.game.data.model.LevelProgress component2() {
        return null;
    }
    
    public final boolean component3() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.colors.game.presentation.viewmodel.LevelItem copy(@org.jetbrains.annotations.NotNull()
    com.colors.game.data.model.Level level, @org.jetbrains.annotations.Nullable()
    com.colors.game.data.model.LevelProgress progress, boolean isUnlocked) {
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