package com.colors.game.presentation.viewmodel;

import com.colors.game.data.remote.LeaderboardRepository;
import com.colors.game.data.remote.PlayGamesManager;
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
public final class LeaderboardViewModel_Factory implements Factory<LeaderboardViewModel> {
  private final Provider<LeaderboardRepository> leaderboardRepoProvider;

  private final Provider<PlayGamesManager> playGamesManagerProvider;

  public LeaderboardViewModel_Factory(Provider<LeaderboardRepository> leaderboardRepoProvider,
      Provider<PlayGamesManager> playGamesManagerProvider) {
    this.leaderboardRepoProvider = leaderboardRepoProvider;
    this.playGamesManagerProvider = playGamesManagerProvider;
  }

  @Override
  public LeaderboardViewModel get() {
    return newInstance(leaderboardRepoProvider.get(), playGamesManagerProvider.get());
  }

  public static LeaderboardViewModel_Factory create(
      Provider<LeaderboardRepository> leaderboardRepoProvider,
      Provider<PlayGamesManager> playGamesManagerProvider) {
    return new LeaderboardViewModel_Factory(leaderboardRepoProvider, playGamesManagerProvider);
  }

  public static LeaderboardViewModel newInstance(LeaderboardRepository leaderboardRepo,
      PlayGamesManager playGamesManager) {
    return new LeaderboardViewModel(leaderboardRepo, playGamesManager);
  }
}
