package com.colors.game.data.remote;

import android.content.Context;
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
public final class AdManager_Factory implements Factory<AdManager> {
  private final Provider<Context> contextProvider;

  private final Provider<BillingManager> billingManagerProvider;

  public AdManager_Factory(Provider<Context> contextProvider,
      Provider<BillingManager> billingManagerProvider) {
    this.contextProvider = contextProvider;
    this.billingManagerProvider = billingManagerProvider;
  }

  @Override
  public AdManager get() {
    return newInstance(contextProvider.get(), billingManagerProvider.get());
  }

  public static AdManager_Factory create(Provider<Context> contextProvider,
      Provider<BillingManager> billingManagerProvider) {
    return new AdManager_Factory(contextProvider, billingManagerProvider);
  }

  public static AdManager newInstance(Context context, BillingManager billingManager) {
    return new AdManager(context, billingManager);
  }
}
