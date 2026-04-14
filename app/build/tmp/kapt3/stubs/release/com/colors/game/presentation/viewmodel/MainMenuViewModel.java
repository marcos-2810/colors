package com.colors.game.presentation.viewmodel;

import androidx.lifecycle.ViewModel;
import com.colors.game.data.repository.GameStateRepository;
import com.colors.game.data.repository.SettingsRepository;
import com.colors.game.domain.LevelGenerator;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u000e\u001a\u00020\u000fR\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0010"}, d2 = {"Lcom/colors/game/presentation/viewmodel/MainMenuViewModel;", "Landroidx/lifecycle/ViewModel;", "gameStateRepo", "Lcom/colors/game/data/repository/GameStateRepository;", "settingsRepo", "Lcom/colors/game/data/repository/SettingsRepository;", "(Lcom/colors/game/data/repository/GameStateRepository;Lcom/colors/game/data/repository/SettingsRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/colors/game/presentation/viewmodel/MainMenuUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "markTutorialCompleted", "", "app_release"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class MainMenuViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.colors.game.data.repository.GameStateRepository gameStateRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.colors.game.data.repository.SettingsRepository settingsRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.colors.game.presentation.viewmodel.MainMenuUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.colors.game.presentation.viewmodel.MainMenuUiState> uiState = null;
    
    @javax.inject.Inject()
    public MainMenuViewModel(@org.jetbrains.annotations.NotNull()
    com.colors.game.data.repository.GameStateRepository gameStateRepo, @org.jetbrains.annotations.NotNull()
    com.colors.game.data.repository.SettingsRepository settingsRepo) {
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
}