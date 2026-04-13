package com.colors.game.presentation.viewmodel;

import androidx.lifecycle.ViewModel;
import com.colors.game.data.model.AppSettings;
import com.colors.game.data.model.Language;
import com.colors.game.data.repository.SettingsRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.SharingStarted;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u001c\u0010\u0014\u001a\u00020\u000b2\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0016H\u0002R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/colors/game/presentation/viewmodel/SettingsViewModel;", "Landroidx/lifecycle/ViewModel;", "settingsRepo", "Lcom/colors/game/data/repository/SettingsRepository;", "(Lcom/colors/game/data/repository/SettingsRepository;)V", "settings", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/colors/game/data/model/AppSettings;", "getSettings", "()Lkotlinx/coroutines/flow/StateFlow;", "setDaltonicMode", "", "enabled", "", "setLanguage", "language", "Lcom/colors/game/data/model/Language;", "setMusic", "setSound", "setVibration", "update", "transform", "Lkotlin/Function1;", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class SettingsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.colors.game.data.repository.SettingsRepository settingsRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.colors.game.data.model.AppSettings> settings = null;
    
    @javax.inject.Inject()
    public SettingsViewModel(@org.jetbrains.annotations.NotNull()
    com.colors.game.data.repository.SettingsRepository settingsRepo) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.colors.game.data.model.AppSettings> getSettings() {
        return null;
    }
    
    public final void setDaltonicMode(boolean enabled) {
    }
    
    public final void setSound(boolean enabled) {
    }
    
    public final void setMusic(boolean enabled) {
    }
    
    public final void setVibration(boolean enabled) {
    }
    
    public final void setLanguage(@org.jetbrains.annotations.NotNull()
    com.colors.game.data.model.Language language) {
    }
    
    private final void update(kotlin.jvm.functions.Function1<? super com.colors.game.data.model.AppSettings, com.colors.game.data.model.AppSettings> transform) {
    }
}