package com.colors.game.data.local;

import android.content.Context;
import androidx.datastore.core.DataStore;
import androidx.datastore.preferences.core.Preferences;
import com.colors.game.data.model.AppSettings;
import com.colors.game.data.model.GameState;
import com.colors.game.data.model.LevelProgress;
import dagger.hilt.android.qualifiers.ApplicationContext;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u000f\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0019\u001a\u00020\u001aH\u0086@\u00a2\u0006\u0002\u0010\u001bJ#\u0010\u001c\u001a\u0004\u0018\u00010\u00132\b\u0010\u001d\u001a\u0004\u0018\u00010\u00132\b\u0010\u001e\u001a\u0004\u0018\u00010\u0013H\u0002\u00a2\u0006\u0002\u0010\u001fJ\u0016\u0010 \u001a\u00020\u001a2\u0006\u0010!\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\"J\u0016\u0010#\u001a\u00020\u001a2\u0006\u0010$\u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010%J\u0016\u0010&\u001a\u00020\u001a2\u0006\u0010\'\u001a\u00020\u0014H\u0086@\u00a2\u0006\u0002\u0010(R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010\u0011\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u00120\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000eR\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000e\u00a8\u0006)"}, d2 = {"Lcom/colors/game/data/local/DataStoreManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "KEY_ACTIVE_GAME", "Landroidx/datastore/preferences/core/Preferences$Key;", "", "KEY_PROGRESS_MAP", "KEY_SETTINGS", "activeGameFlow", "Lkotlinx/coroutines/flow/Flow;", "Lcom/colors/game/data/model/GameState;", "getActiveGameFlow", "()Lkotlinx/coroutines/flow/Flow;", "json", "Lkotlinx/serialization/json/Json;", "progressMapFlow", "", "", "Lcom/colors/game/data/model/LevelProgress;", "getProgressMapFlow", "settingsFlow", "Lcom/colors/game/data/model/AppSettings;", "getSettingsFlow", "clearActiveGame", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "minOfNotNull", "a", "b", "(Ljava/lang/Integer;Ljava/lang/Integer;)Ljava/lang/Integer;", "saveActiveGame", "state", "(Lcom/colors/game/data/model/GameState;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveSettings", "settings", "(Lcom/colors/game/data/model/AppSettings;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateLevelProgress", "progress", "(Lcom/colors/game/data/model/LevelProgress;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
public final class DataStoreManager {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.serialization.json.Json json = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> KEY_SETTINGS = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> KEY_ACTIVE_GAME = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> KEY_PROGRESS_MAP = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<com.colors.game.data.model.AppSettings> settingsFlow = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<com.colors.game.data.model.GameState> activeGameFlow = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.Map<java.lang.Integer, com.colors.game.data.model.LevelProgress>> progressMapFlow = null;
    
    @javax.inject.Inject()
    public DataStoreManager(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.colors.game.data.model.AppSettings> getSettingsFlow() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveSettings(@org.jetbrains.annotations.NotNull()
    com.colors.game.data.model.AppSettings settings, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.colors.game.data.model.GameState> getActiveGameFlow() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveActiveGame(@org.jetbrains.annotations.NotNull()
    com.colors.game.data.model.GameState state, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object clearActiveGame(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.Map<java.lang.Integer, com.colors.game.data.model.LevelProgress>> getProgressMapFlow() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateLevelProgress(@org.jetbrains.annotations.NotNull()
    com.colors.game.data.model.LevelProgress progress, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.Integer minOfNotNull(java.lang.Integer a, java.lang.Integer b) {
        return null;
    }
}