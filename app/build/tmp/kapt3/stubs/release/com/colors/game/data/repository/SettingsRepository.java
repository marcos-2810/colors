package com.colors.game.data.repository;

import com.colors.game.data.local.DataStoreManager;
import com.colors.game.data.model.AppSettings;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0007H\u0086@\u00a2\u0006\u0002\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\u000e"}, d2 = {"Lcom/colors/game/data/repository/SettingsRepository;", "", "dataStore", "Lcom/colors/game/data/local/DataStoreManager;", "(Lcom/colors/game/data/local/DataStoreManager;)V", "settingsFlow", "Lkotlinx/coroutines/flow/Flow;", "Lcom/colors/game/data/model/AppSettings;", "getSettingsFlow", "()Lkotlinx/coroutines/flow/Flow;", "save", "", "settings", "(Lcom/colors/game/data/model/AppSettings;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
public final class SettingsRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.colors.game.data.local.DataStoreManager dataStore = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<com.colors.game.data.model.AppSettings> settingsFlow = null;
    
    @javax.inject.Inject()
    public SettingsRepository(@org.jetbrains.annotations.NotNull()
    com.colors.game.data.local.DataStoreManager dataStore) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.colors.game.data.model.AppSettings> getSettingsFlow() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object save(@org.jetbrains.annotations.NotNull()
    com.colors.game.data.model.AppSettings settings, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}