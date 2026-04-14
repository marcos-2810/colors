package com.colors.game.presentation.viewmodel;

import android.app.Activity;
import androidx.lifecycle.ViewModel;
import com.colors.game.data.remote.BillingManager;
import com.colors.game.data.repository.GameStateRepository;
import com.colors.game.data.repository.SettingsRepository;
import com.colors.game.domain.LevelGenerator;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0014J\u0016\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0086@\u00a2\u0006\u0002\u0010\u0019R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u001a"}, d2 = {"Lcom/colors/game/presentation/viewmodel/MainMenuViewModel;", "Landroidx/lifecycle/ViewModel;", "gameStateRepo", "Lcom/colors/game/data/repository/GameStateRepository;", "settingsRepo", "Lcom/colors/game/data/repository/SettingsRepository;", "billingManager", "Lcom/colors/game/data/remote/BillingManager;", "(Lcom/colors/game/data/repository/GameStateRepository;Lcom/colors/game/data/repository/SettingsRepository;Lcom/colors/game/data/remote/BillingManager;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/colors/game/presentation/viewmodel/MainMenuUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "markTutorialCompleted", "", "purchasePremium", "activity", "Landroid/app/Activity;", "redeemCode", "", "code", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class MainMenuViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.colors.game.data.repository.GameStateRepository gameStateRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.colors.game.data.repository.SettingsRepository settingsRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.colors.game.data.remote.BillingManager billingManager = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.colors.game.presentation.viewmodel.MainMenuUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.colors.game.presentation.viewmodel.MainMenuUiState> uiState = null;
    
    @javax.inject.Inject()
    public MainMenuViewModel(@org.jetbrains.annotations.NotNull()
    com.colors.game.data.repository.GameStateRepository gameStateRepo, @org.jetbrains.annotations.NotNull()
    com.colors.game.data.repository.SettingsRepository settingsRepo, @org.jetbrains.annotations.NotNull()
    com.colors.game.data.remote.BillingManager billingManager) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.colors.game.presentation.viewmodel.MainMenuUiState> getUiState() {
        return null;
    }
    
    /**
     * Marcar el tutorial como completado y persistirlo
     */
    public final void markTutorialCompleted() {
    }
    
    /**
     * Abre el flujo de compra de Google Play para el upgrade Premium.
     */
    public final void purchasePremium(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
    }
    
    /**
     * Validates [code] against the known promo codes.
     * Returns true if valid (and persists the unlock), false otherwise.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object redeemCode(@org.jetbrains.annotations.NotNull()
    java.lang.String code, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
}