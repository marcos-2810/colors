package com.colors.game.presentation.viewmodel;

import com.colors.game.data.repository.GameStateRepository;
import com.colors.game.data.repository.LevelRepository;
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
public final class LevelSelectorViewModel_Factory implements Factory<LevelSelectorViewModel> {
  private final Provider<LevelRepository> levelRepoProvider;

  private final Provider<GameStateRepository> gameStateRepoProvider;

  public LevelSelectorViewModel_Factory(Provider<LevelRepository> levelRepoProvider,
      Provider<GameStateRepository> gameStateRepoProvider) {
    this.levelRepoProvider = levelRepoProvider;
    this.gameStateRepoProvider = gameStateRepoProvider;
  }

  @Override
  public LevelSelectorViewModel get() {
    return newInstance(levelRepoProvider.get(), gameStateRepoProvider.get());
  }

  public static LevelSelectorViewModel_Factory create(Provider<LevelRepository> levelRepoProvider,
      Provider<GameStateRepository> gameStateRepoProvider) {
    return new LevelSelectorViewModel_Factory(levelRepoProvider, gameStateRepoProvider);
  }

  public static LevelSelectorViewModel newInstance(LevelRepository levelRepo,
      GameStateRepository gameStateRepo) {
    return new LevelSelectorViewModel(levelRepo, gameStateRepo);
  }
}
