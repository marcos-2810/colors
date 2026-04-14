package com.colors.game.di;

import com.colors.game.domain.GreedySolver;
import com.colors.game.domain.LevelGenerator;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class AppModule_ProvideLevelGeneratorFactory implements Factory<LevelGenerator> {
  private final Provider<GreedySolver> solverProvider;

  public AppModule_ProvideLevelGeneratorFactory(Provider<GreedySolver> solverProvider) {
    this.solverProvider = solverProvider;
  }

  @Override
  public LevelGenerator get() {
    return provideLevelGenerator(solverProvider.get());
  }

  public static AppModule_ProvideLevelGeneratorFactory create(
      Provider<GreedySolver> solverProvider) {
    return new AppModule_ProvideLevelGeneratorFactory(solverProvider);
  }

  public static LevelGenerator provideLevelGenerator(GreedySolver solver) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideLevelGenerator(solver));
  }
}
