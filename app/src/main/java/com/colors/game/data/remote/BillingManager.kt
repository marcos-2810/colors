package com.colors.game.data.remote

import android.app.Activity
import android.content.Context
import com.android.billingclient.api.*
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

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
 *   Observe [isPremium] to know current premium status.
 *   Call [launchPurchaseFlow] with the current Activity to open the payment sheet.
 */
@Singleton
class BillingManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        const val PRODUCT_ID_PREMIUM = "premium"
    }

    private val _isPremium      = MutableStateFlow(false)
    val isPremium: StateFlow<Boolean> = _isPremium.asStateFlow()

    private val _productDetails = MutableStateFlow<ProductDetails?>(null)
    /** True once the Play Store has returned the product details (price, name). */
    val canPurchase: StateFlow<Boolean> get() = MutableStateFlow(_productDetails.value != null)

    // ── Billing client ────────────────────────────────────────────────────────

    private val purchasesUpdatedListener = PurchasesUpdatedListener { result, purchases ->
        when (result.responseCode) {
            BillingClient.BillingResponseCode.OK -> purchases?.forEach { handlePurchase(it) }
            BillingClient.BillingResponseCode.USER_CANCELED -> { /* user dismissed — no-op */ }
            else -> { /* other errors — silently ignore */ }
        }
    }

    private val billingClient: BillingClient = BillingClient.newBuilder(context)
        .setListener(purchasesUpdatedListener)
        .enablePendingPurchases()   // billing-ktx 6.x API (no params)
        .build()

    init {
        connect()
    }

    // ── Connection ────────────────────────────────────────────────────────────

    private fun connect() {
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(result: BillingResult) {
                if (result.responseCode == BillingClient.BillingResponseCode.OK) {
                    checkExistingPurchases()
                    queryProductDetails()
                }
            }
            override fun onBillingServiceDisconnected() {
                // Play Store disconnected — retry on next interaction
            }
        })
    }

    // ── Check existing purchases on startup ───────────────────────────────────

    private fun checkExistingPurchases() {
        val params = QueryPurchasesParams.newBuilder()
            .setProductType(BillingClient.ProductType.INAPP)
            .build()
        billingClient.queryPurchasesAsync(params) { result, purchases ->
            if (result.responseCode == BillingClient.BillingResponseCode.OK) {
                _isPremium.value = purchases.any { it.isPremiumPurchase() }
            }
        }
    }

    // ── Query product details (price, name) ───────────────────────────────────

    private fun queryProductDetails() {
        val params = QueryProductDetailsParams.newBuilder()
            .setProductList(
                listOf(
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId(PRODUCT_ID_PREMIUM)
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build()
                )
            )
            .build()
        billingClient.queryProductDetailsAsync(params) { result, detailsList ->
            if (result.responseCode == BillingClient.BillingResponseCode.OK) {
                _productDetails.value = detailsList.firstOrNull()
            }
        }
    }

    // ── Launch purchase UI ────────────────────────────────────────────────────

    /**
     * Opens the Google Play payment sheet.
     * Must be called from an Activity (pass [activity] from the Composable screen).
     * Does nothing if the product details are not yet loaded or user is already premium.
     */
    fun launchPurchaseFlow(activity: Activity) {
        if (_isPremium.value) return
        val details = _productDetails.value ?: run {
            // Details not loaded yet — reconnect and try again
            if (!billingClient.isReady) connect()
            return
        }

        val params = BillingFlowParams.newBuilder()
            .setProductDetailsParamsList(
                listOf(
                    BillingFlowParams.ProductDetailsParams.newBuilder()
                        .setProductDetails(details)
                        .build()
                )
            )
            .build()

        billingClient.launchBillingFlow(activity, params)
    }

    // ── Handle a completed purchase ───────────────────────────────────────────

    private fun handlePurchase(purchase: Purchase) {
        if (!purchase.isPremiumPurchase()) return

        _isPremium.value = true

        // Acknowledge within 3 days to prevent auto-refund
        if (!purchase.isAcknowledged) {
            val params = AcknowledgePurchaseParams.newBuilder()
                .setPurchaseToken(purchase.purchaseToken)
                .build()
            billingClient.acknowledgePurchase(params) { /* fire-and-forget */ }
        }
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    private fun Purchase.isPremiumPurchase(): Boolean =
        products.contains(PRODUCT_ID_PREMIUM) &&
                purchaseState == Purchase.PurchaseState.PURCHASED
}
