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
public final class SettingsRepository_Factory implements Factory<SettingsRepository> {
  private final Provider<DataStoreManager> dataStoreProvider;

  public SettingsRepository_Factory(Provider<DataStoreManager> dataStoreProvider) {
    this.dataStoreProvider = dataStoreProvider;
  }

  @Override
  public SettingsRepository get() {
    return newInstance(dataStoreProvider.get());
  }

  public static SettingsRepository_Factory create(Provider<DataStoreManager> dataStoreProvider) {
    return new SettingsRepository_Factory(dataStoreProvider);
  }

  public static SettingsRepository newInstance(DataStoreManager dataStore) {
    return new SettingsRepository(dataStore);
  }
}
