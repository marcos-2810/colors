package com.colors.game.presentation.viewmodel;

import com.colors.game.data.repository.GameStateRepository;
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
public final class MainMenuViewModel_Factory implements Factory<MainMenuViewModel> {
  private final Provider<GameStateRepository> gameStateRepoProvider;

  private final Provider<SettingsRepository> settingsRepoProvider;

  public MainMenuViewModel_Factory(Provider<GameStateRepository> gameStateRepoProvider,
      Provider<SettingsRepository> settingsRepoProvider) {
    this.gameStateRepoProvider = gameStateRepoProvider;
    this.settingsRepoProvider = settingsRepoProvider;
  }

  @Override
  public MainMenuViewModel get() {
    return newInstance(gameStateRepoProvider.get(), settingsRepoProvider.get());
  }

  public static MainMenuViewModel_Factory create(
      Provider<GameStateRepository> gameStateRepoProvider,
      Provider<SettingsRepository> settingsRepoProvider) {
    return new MainMenuViewModel_Factory(gameStateRepoProvider, settingsRepoProvider);
  }

  public static MainMenuViewModel newInstance(GameStateRepository gameStateRepo,
      SettingsRepository settingsRepo) {
    return new MainMenuViewModel(gameStateRepo, settingsRepo);
  }
}
