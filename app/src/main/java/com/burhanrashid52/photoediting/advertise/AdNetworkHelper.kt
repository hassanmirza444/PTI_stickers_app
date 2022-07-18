package app.thecity.advertise

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.LinearLayout
import app.thecity.advertise.AdNetworkHelper
import com.burhanrashid52.photoediting.R
import com.facebook.ads.AdSettings
import com.facebook.ads.AdSettings.IntegrationErrorMode
import com.facebook.ads.AudienceNetworkAds
import com.facebook.ads.BuildConfig
import com.google.android.gms.ads.MobileAds
import com.unity3d.ads.IUnityAdsInitializationListener
import com.unity3d.ads.IUnityAdsShowListener
import com.unity3d.ads.UnityAds
import com.unity3d.ads.UnityAds.*
import com.unity3d.services.banners.BannerErrorInfo
import com.unity3d.services.banners.BannerView
import com.unity3d.services.banners.BannerView.IListener
import com.unity3d.services.banners.UnityBannerSize

class AdNetworkHelper(private val activity: Activity) {



    fun loadUnityBannerAd(enable: Boolean) {
        if (!AdConfig.ad_enable || !enable) return
        val ad_container = activity.findViewById<LinearLayout>(R.id.ad_container)
        ad_container.removeAllViews()

        val bottomBanner = BannerView(activity, AdConfig.ad_unity_banner_unit_id, unityBannerSize)
        bottomBanner.listener = object : IListener {
            override fun onBannerLoaded(bannerView: BannerView) {
                ad_container.visibility = View.VISIBLE
                Log.d(TAG, "ready")
            }

            override fun onBannerClick(bannerView: BannerView) {}
            override fun onBannerFailedToLoad(
                bannerView: BannerView,
                bannerErrorInfo: BannerErrorInfo
            ) {
                Log.d(TAG, "Banner Error$bannerErrorInfo")
                ad_container.visibility = View.GONE
            }

            override fun onBannerLeftApplication(bannerView: BannerView) {}
        }
        ad_container.addView(bottomBanner)
        bottomBanner.load()


    }

    fun loadInterstitialAd(enable: Boolean) {
        if (!AdConfig.ad_enable || !enable) return
        if (AdConfig.ad_network === AdConfig.AdNetworkType.UNITY) {
        }

    }

    fun showInterstitialAd(enable: Boolean): Boolean {
        if (!AdConfig.ad_enable || !enable) return false
        if (AdConfig.ad_network === AdConfig.AdNetworkType.UNITY) {
            //if (!UnityAds.isReady(adConfig.ad_unity_interstitial_unit_id)) return false;
            //DataApp.pref().setIntersCounter(0);
            UnityAds.show(
                activity,
                AdConfig.ad_unity_interstitial_unit_id,
                object : IUnityAdsShowListener {
                    override fun onUnityAdsShowFailure(
                        s: String,
                        unityAdsShowError: UnityAdsShowError,
                        s1: String
                    ) {
                        Log.d("Ads Error", unityAdsShowError.name + s)
                    }

                    override fun onUnityAdsShowStart(s: String) {

                        loadInterstitialAd(enable)
                    }

                    override fun onUnityAdsShowClick(s: String) {}
                    override fun onUnityAdsShowComplete(
                        s: String,
                        unityAdsShowCompletionState: UnityAdsShowCompletionState
                    ) {
                    }
                })
        }
        return true

    }

    // Step 2 - Determine the screen width (less decorations) to use for the ad width.

    private val unityBannerSize: UnityBannerSize
        private get() {
            val display = activity.windowManager.defaultDisplay
            val outMetrics = DisplayMetrics()
            display.getMetrics(outMetrics)
            val widthPixels = outMetrics.widthPixels.toFloat()
            val density = outMetrics.density
            val adWidth = (widthPixels / density).toInt()
            return UnityBannerSize(adWidth, 50)
        }



    companion object {
        private val TAG = AdNetworkHelper::class.java.simpleName

        @JvmStatic
        fun init(context: Context) {
            if (!AdConfig.ad_enable) return
            if (AdConfig.ad_network === AdConfig.AdNetworkType.ADMOB) {
                // Init firebase ads.
                MobileAds.initialize(context)
            } else if (AdConfig.ad_network === AdConfig.AdNetworkType.FAN) {
                AudienceNetworkAds.initialize(context)
                AdSettings.setIntegrationErrorMode(IntegrationErrorMode.INTEGRATION_ERROR_CALLBACK_MODE)
            } else if (AdConfig.ad_network === AdConfig.AdNetworkType.UNITY) {
                UnityAds.initialize(
                    context,
                    AdConfig.ad_unity_game_id,
                    BuildConfig.DEBUG,
                    object : IUnityAdsInitializationListener {
                        override fun onInitializationComplete() {
                            Log.d(TAG, "Unity Ads Initialization Complete")
                            Log.d(TAG, "Unity Ads Game ID : " + AdConfig.ad_unity_game_id)
                        }

                        override fun onInitializationFailed(
                            error: UnityAdsInitializationError,
                            message: String
                        ) {
                            Log.d(TAG, "Unity Ads Initialization Failed: [$error] $message")
                        }
                    })
            }
        }

        private fun dpToPx(c: Context, dp: Int): Int {
            val r = c.resources
            return Math.round(
                TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    dp.toFloat(),
                    r.displayMetrics
                )
            )
        }
    }

    init {

    }
}