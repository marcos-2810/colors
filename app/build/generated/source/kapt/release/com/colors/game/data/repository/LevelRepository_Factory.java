package com.colors.game.data.repository;

import android.content.Context;
import com.colors.game.domain.LevelGenerator;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class LevelRepository_Factory implements Factory<LevelRepository> {
  private final Provider<LevelGenerator> generatorProvider;

  private final Provider<Context> contextProvider;

  public LevelRepository_Factory(Provider<LevelGenerator> generatorProvider,
      Provider<Context> contextProvider) {
    this.generatorProvider = generatorProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public LevelRepository get() {
    return newInstance(generatorProvider.get(), contextProvider.get());
  }

  public static LevelRepository_Factory create(Provider<LevelGenerator> generatorProvider,
      Provider<Context> contextProvider) {
    return new LevelRepository_Factory(generatorProvider, contextProvider);
  }

  public static LevelRepository newInstance(LevelGenerator generator, Context context) {
    return new LevelRepository(generator, context);
  }
}
