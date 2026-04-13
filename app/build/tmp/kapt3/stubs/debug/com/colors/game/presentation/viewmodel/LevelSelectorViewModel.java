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

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J*\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\t2\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00170\u0015H\u0002J\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0013\u001a\u00020\tR\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u001a"}, d2 = {"Lcom/colors/game/presentation/viewmodel/LevelSelectorViewModel;", "Landroidx/lifecycle/ViewModel;", "levelRepo", "Lcom/colors/game/data/repository/LevelRepository;", "gameStateRepo", "Lcom/colors/game/data/repository/GameStateRepository;", "(Lcom/colors/game/data/repository/LevelRepository;Lcom/colors/game/data/repository/GameStateRepository;)V", "_difficulty", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/colors/game/data/model/Difficulty;", "_uiState", "Lcom/colors/game/presentation/viewmodel/LevelSelectorUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "buildItems", "", "Lcom/colors/game/presentation/viewmodel/LevelItem;", "difficulty", "progressMap", "", "", "Lcom/colors/game/data/model/LevelProgress;", "selectDifficulty", "", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class LevelSelectorViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.colors.game.data.repository.LevelRepository levelRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.colors.game.data.repository.GameStateRepository gameStateRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.colors.game.data.model.Difficulty> _difficulty = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.colors.game.presentation.viewmodel.LevelSelectorUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.colors.game.presentation.viewmodel.LevelSelectorUiState> uiState = null;
    
    @javax.inject.Inject()
    public LevelSelectorViewModel(@org.jetbrains.annotations.NotNull()
    com.colors.game.data.repository.LevelRepository levelRepo, @org.jetbrains.annotations.NotNull()
    com.colors.game.data.repository.GameStateRepository gameStateRepo) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.colors.game.presentation.viewmodel.LevelSelectorUiState> getUiState() {
        return null;
    }
    
    public final void selectDifficulty(@org.jetbrains.annotations.NotNull()
    com.colors.game.data.model.Difficulty difficulty) {
    }
    
    private final java.util.List<com.colors.game.presentation.viewmodel.LevelItem> buildItems(com.colors.game.data.model.Difficulty difficulty, java.util.Map<java.lang.Integer, com.colors.game.data.model.LevelProgress> progressMap) {
        return null;
    }
}