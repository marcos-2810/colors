package com.colors.game.data.repository;

import com.colors.game.data.local.DataStoreManager;
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
public final class GameStateRepository_Factory implements Factory<GameStateRepository> {
  private final Provider<DataStoreManager> dataStoreProvider;

  public GameStateRepository_Factory(Provider<DataStoreManager> dataStoreProvider) {
    this.dataStoreProvider = dataStoreProvider;
  }

  @Override
  public GameStateRepository get() {
    return newInstance(dataStoreProvider.get());
  }

  public static GameStateRepository_Factory create(Provider<DataStoreManager> dataStoreProvider) {
    return new GameStateRepository_Factory(dataStoreProvider);
  }

  public static GameStateRepository newInstance(DataStoreManager dataStore) {
    return new GameStateRepository(dataStore);
  }
}
