package com.colors.game.data.remote;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class LeaderboardRepository_Factory implements Factory<LeaderboardRepository> {
  private final Provider<PlayGamesManager> playGamesManagerProvider;

  public LeaderboardRepository_Factory(Provider<PlayGamesManager> playGamesManagerProvider) {
    this.playGamesManagerProvider = playGamesManagerProvider;
  }

  @Override
  public LeaderboardRepository get() {
    return newInstance(playGamesManagerProvider.get());
  }

  public static LeaderboardRepository_Factory create(
      Provider<PlayGamesManager> playGamesManagerProvider) {
    return new LeaderboardRepository_Factory(playGamesManagerProvider);
  }

  public static LeaderboardRepository newInstance(PlayGamesManager playGamesManager) {
    return new LeaderboardRepository(playGamesManager);
  }
}
