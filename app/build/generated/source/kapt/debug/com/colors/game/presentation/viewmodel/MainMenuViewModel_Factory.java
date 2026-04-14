package com.colors.game.presentation.viewmodel;

import com.colors.game.data.remote.BillingManager;
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

  private final Provider<BillingManager> billingManagerProvider;

  public MainMenuViewModel_Factory(Provider<GameStateRepository> gameStateRepoProvider,
      Provider<SettingsRepository> settingsRepoProvider,
      Provider<BillingManager> billingManagerProvider) {
    this.gameStateRepoProvider = gameStateRepoProvider;
    this.settingsRepoProvider = settingsRepoProvider;
    this.billingManagerProvider = billingManagerProvider;
  }

  @Override
  public MainMenuViewModel get() {
    return newInstance(gameStateRepoProvider.get(), settingsRepoProvider.get(), billingManagerProvider.get());
  }

  public static MainMenuViewModel_Factory create(
      Provider<GameStateRepository> gameStateRepoProvider,
      Provider<SettingsRepository> settingsRepoProvider,
      Provider<BillingManager> billingManagerProvider) {
    return new MainMenuViewModel_Factory(gameStateRepoProvider, settingsRepoProvider, billingManagerProvider);
  }

  public static MainMenuViewModel newInstance(GameStateRepository gameStateRepo,
      SettingsRepository settingsRepo, BillingManager billingManager) {
    return new MainMenuViewModel(gameStateRepo, settingsRepo, billingManager);
  }
}
