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

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lcom/colors/game/presentation/viewmodel/AdTrigger;", "", "(Ljava/lang/String;I)V", "NONE", "LEVEL_COMPLETED", "TIME_LIMIT", "app_debug"})
public enum AdTrigger {
    /*public static final*/ NONE /* = new NONE() */,
    /*public static final*/ LEVEL_COMPLETED /* = new LEVEL_COMPLETED() */,
    /*public static final*/ TIME_LIMIT /* = new TIME_LIMIT() */;
    
    AdTrigger() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.colors.game.presentation.viewmodel.AdTrigger> getEntries() {
        return null;
    }
}