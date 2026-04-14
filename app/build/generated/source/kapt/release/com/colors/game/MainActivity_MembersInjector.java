package com.colors.game;

import com.colors.game.data.remote.PlayGamesManager;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class MainActivity_MembersInjector implements MembersInjector<MainActivity> {
  private final Provider<PlayGamesManager> playGamesManagerProvider;

  public MainActivity_MembersInjector(Provider<PlayGamesManager> playGamesManagerProvider) {
    this.playGamesManagerProvider = playGamesManagerProvider;
  }

  public static MembersInjector<MainActivity> create(
      Provider<PlayGamesManager> playGamesManagerProvider) {
    return new MainActivity_MembersInjector(playGamesManagerProvider);
  }

  @Override
  public void injectMembers(MainActivity instance) {
    injectPlayGamesManager(instance, playGamesManagerProvider.get());
  }

  @InjectedFieldSignature("com.colors.game.MainActivity.playGamesManager")
  public static void injectPlayGamesManager(MainActivity instance,
      PlayGamesManager playGamesManager) {
    instance.playGamesManager = playGamesManager;
  }
}
