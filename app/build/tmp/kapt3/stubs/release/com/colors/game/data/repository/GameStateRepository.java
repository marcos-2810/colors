package com.colors.game.data.repository;

import com.colors.game.data.local.DataStoreManager;
import com.colors.game.data.model.GameState;
import com.colors.game.data.model.LevelProgress;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u000f\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0011J\u0016\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\rH\u0086@\u00a2\u0006\u0002\u0010\u0014J\u0016\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u0007H\u0086@\u00a2\u0006\u0002\u0010\u0017R\u0019\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010\n\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\t\u00a8\u0006\u0018"}, d2 = {"Lcom/colors/game/data/repository/GameStateRepository;", "", "dataStore", "Lcom/colors/game/data/local/DataStoreManager;", "(Lcom/colors/game/data/local/DataStoreManager;)V", "activeGameFlow", "Lkotlinx/coroutines/flow/Flow;", "Lcom/colors/game/data/model/GameState;", "getActiveGameFlow", "()Lkotlinx/coroutines/flow/Flow;", "progressMapFlow", "", "", "Lcom/colors/game/data/model/LevelProgress;", "getProgressMapFlow", "clearActiveGame", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "recordProgress", "progress", "(Lcom/colors/game/data/model/LevelProgress;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveActiveGame", "state", "(Lcom/colors/game/data/model/GameState;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
public final class GameStateRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.colors.game.data.local.DataStoreManager dataStore = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<com.colors.game.data.model.GameState> activeGameFlow = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.Map<java.lang.Integer, com.colors.game.data.model.LevelProgress>> progressMapFlow = null;
    
    @javax.inject.Inject()
    public GameStateRepository(@org.jetbrains.annotations.NotNull()
    com.colors.game.data.local.DataStoreManager dataStore) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.colors.game.data.model.GameState> getActiveGameFlow() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.Map<java.lang.Integer, com.colors.game.data.model.LevelProgress>> getProgressMapFlow() {
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
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object recordProgress(@org.jetbrains.annotations.NotNull()
    com.colors.game.data.model.LevelProgress progress, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}