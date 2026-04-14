package com.colors.game.presentation.viewmodel;

import androidx.lifecycle.SavedStateHandle;
import com.colors.game.data.remote.AdManager;
import com.colors.game.data.remote.LeaderboardRepository;
import com.colors.game.data.repository.GameStateRepository;
import com.colors.game.data.repository.LevelRepository;
import com.colors.game.data.repository.SettingsRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class GameViewModel_Factory implements Factory<GameViewModel> {
  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private final Provider<LevelRepository> levelRepoProvider;

  private final Provider<GameStateRepository> gameStateRepoProvider;

  private final Provider<SettingsRepository> settingsRepoProvider;

  private final Provider<LeaderboardRepository> leaderboardRepoProvider;

  private final Provider<AdManager> adManagerProvider;

  public GameViewModel_Factory(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<LevelRepository> levelRepoProvider,
      Provider<GameStateRepository> gameStateRepoProvider,
      Provider<SettingsRepository> settingsRepoProvider,
      Provider<LeaderboardRepository> leaderboardRepoProvider,
      Provider<AdManager> adManagerProvider) {
    this.savedStateHandleProvider = savedStateHandleProvider;
    this.levelRepoProvider = levelRepoProvider;
    this.gameStateRepoProvider = gameStateRepoProvider;
    this.settingsRepoProvider = settingsRepoProvider;
    this.leaderboardRepoProvider = leaderboardRepoProvider;
    this.adManagerProvider = adManagerProvider;
  }

  @Override
  public GameViewModel get() {
    return newInstance(savedStateHandleProvider.get(), levelRepoProvider.get(), gameStateRepoProvider.get(), settingsRepoProvider.get(), leaderboardRepoProvider.get(), adManagerProvider.get());
  }

  public static GameViewModel_Factory create(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<LevelRepository> levelRepoProvider,
      Provider<GameStateRepository> gameStateRepoProvider,
      Provider<SettingsRepository> settingsRepoProvider,
      Provider<LeaderboardRepository> leaderboardRepoProvider,
      Provider<AdManager> adManagerProvider) {
    return new GameViewModel_Factory(savedStateHandleProvider, levelRepoProvider, gameStateRepoProvider, settingsRepoProvider, leaderboardRepoProvider, adManagerProvider);
  }

  public static GameViewModel newInstance(SavedStateHandle savedStateHandle,
      LevelRepository levelRepo, GameStateRepository gameStateRepo, SettingsRepository settingsRepo,
      LeaderboardRepository leaderboardRepo, AdManager adManager) {
    return new GameViewModel(savedStateHandle, levelRepo, gameStateRepo, settingsRepo, leaderboardRepo, adManager);
  }
}
