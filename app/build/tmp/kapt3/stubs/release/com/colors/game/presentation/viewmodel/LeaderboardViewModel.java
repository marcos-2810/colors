package com.colors.game.presentation.viewmodel;

import androidx.lifecycle.ViewModel;
import com.colors.game.data.model.LeaderboardEntry;
import com.colors.game.data.model.LeaderboardType;
import com.colors.game.data.remote.LeaderboardRepository;
import com.colors.game.data.remote.PlayGamesManager;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u000e\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u0018\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0006\u0010\u0016\u001a\u00020\u000fJ\u000e\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u0011R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0019"}, d2 = {"Lcom/colors/game/presentation/viewmodel/LeaderboardViewModel;", "Landroidx/lifecycle/ViewModel;", "leaderboardRepo", "Lcom/colors/game/data/remote/LeaderboardRepository;", "playGamesManager", "Lcom/colors/game/data/remote/PlayGamesManager;", "(Lcom/colors/game/data/remote/LeaderboardRepository;Lcom/colors/game/data/remote/PlayGamesManager;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/colors/game/presentation/viewmodel/LeaderboardUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "loadAll", "", "levelId", "", "loadForLevel", "loadType", "type", "Lcom/colors/game/data/model/LeaderboardType;", "refresh", "selectTab", "index", "app_release"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class LeaderboardViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.colors.game.data.remote.LeaderboardRepository leaderboardRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.colors.game.data.remote.PlayGamesManager playGamesManager = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.colors.game.presentation.viewmodel.LeaderboardUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.colors.game.presentation.viewmodel.LeaderboardUiState> uiState = null;
    
    @javax.inject.Inject()
    public LeaderboardViewModel(@org.jetbrains.annotations.NotNull()
    com.colors.game.data.remote.LeaderboardRepository leaderboardRepo, @org.jetbrains.annotations.NotNull()
    com.colors.game.data.remote.PlayGamesManager playGamesManager) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.colors.game.presentation.viewmodel.LeaderboardUiState> getUiState() {
        return null;
    }
    
    /**
     * Load (or reload) leaderboards for [levelId]. Idempotent if same level.
     */
    public final void loadForLevel(int levelId) {
    }
    
    /**
     * Force-refresh the current level's leaderboards.
     */
    public final void refresh() {
    }
    
    public final void selectTab(int index) {
    }
    
    private final void loadAll(int levelId) {
    }
    
    private final void loadType(int levelId, com.colors.game.data.model.LeaderboardType type) {
    }
}