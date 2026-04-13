package com.colors.game.di;

import com.colors.game.domain.GreedySolver;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class AppModule_ProvideGreedySolverFactory implements Factory<GreedySolver> {
  @Override
  public GreedySolver get() {
    return provideGreedySolver();
  }

  public static AppModule_ProvideGreedySolverFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static GreedySolver provideGreedySolver() {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideGreedySolver());
  }

  private static final class InstanceHolder {
    private static final AppModule_ProvideGreedySolverFactory INSTANCE = new AppModule_ProvideGreedySolverFactory();
  }
}
