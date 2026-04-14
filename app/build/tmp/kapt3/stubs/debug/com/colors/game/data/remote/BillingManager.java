package com.colors.game.data.remote;

import android.app.Activity;
import android.content.Context;
import com.android.billingclient.api.*;
import dagger.hilt.android.qualifiers.ApplicationContext;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Manages the Google Play Billing one-time purchase for the Premium upgrade.
 *
 * Prerequisites (one-time setup in Play Console):
 * ─────────────────────────────────────────────────
 * 1. In Play Console → your app → Monetize → Products → In-app products
 * 2. Create a product with ID "premium", type "One-time product"
 * 3. Set price and activate the product
 *
 * Usage:
 *  Observe [isPremium] to know current premium status.
 *  Call [launchPurchaseFlow] with the current Activity to open the payment sheet.
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0014H\u0002J\u0010\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u000e\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u001bJ\b\u0010\u001c\u001a\u00020\u0014H\u0002J\f\u0010\u001d\u001a\u00020\u0007*\u00020\u0018H\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\r8F\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/colors/game/data/remote/BillingManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "_isPremium", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_productDetails", "Lcom/android/billingclient/api/ProductDetails;", "billingClient", "Lcom/android/billingclient/api/BillingClient;", "canPurchase", "Lkotlinx/coroutines/flow/StateFlow;", "getCanPurchase", "()Lkotlinx/coroutines/flow/StateFlow;", "isPremium", "purchasesUpdatedListener", "Lcom/android/billingclient/api/PurchasesUpdatedListener;", "checkExistingPurchases", "", "connect", "handlePurchase", "purchase", "Lcom/android/billingclient/api/Purchase;", "launchPurchaseFlow", "activity", "Landroid/app/Activity;", "queryProductDetails", "isPremiumPurchase", "Companion", "app_debug"})
public final class BillingManager {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PRODUCT_ID_PREMIUM = "premium";
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isPremium = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isPremium = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.android.billingclient.api.ProductDetails> _productDetails = null;
    @org.jetbrains.annotations.NotNull()
    private final com.android.billingclient.api.PurchasesUpdatedListener purchasesUpdatedListener = null;
    @org.jetbrains.annotations.NotNull()
    private final com.android.billingclient.api.BillingClient billingClient = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.colors.game.data.remote.BillingManager.Companion Companion = null;
    
    @javax.inject.Inject()
    public BillingManager(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isPremium() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getCanPurchase() {
        return null;
    }
    
    private final void connect() {
    }
    
    private final void checkExistingPurchases() {
    }
    
    private final void queryProductDetails() {
    }
    
    /**
     * Opens the Google Play payment sheet.
     * Must be called from an Activity (pass [activity] from the Composable screen).
     * Does nothing if the product details are not yet loaded or user is already premium.
     */
    public final void launchPurchaseFlow(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
    }
    
    private final void handlePurchase(com.android.billingclient.api.Purchase purchase) {
    }
    
    private final boolean isPremiumPurchase(com.android.billingclient.api.Purchase $this$isPremiumPurchase) {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/colors/game/data/remote/BillingManager$Companion;", "", "()V", "PRODUCT_ID_PREMIUM", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}