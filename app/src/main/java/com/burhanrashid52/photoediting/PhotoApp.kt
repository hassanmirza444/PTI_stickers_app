package com.burhanrashid52.photoediting

import android.app.Application
import com.burhanrashid52.photoediting.advertise.AdsManager
import com.unity3d.ads.UnityAds

/**
 * Created by Burhanuddin Rashid on 1/23/2018.
 */
class PhotoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        photoApp = this
        AdsManager.getInstance().initialize(this)
        UnityAds.initialize(this,"4771435",true)
    }

    companion object {
        var photoApp: PhotoApp? = null
            private set
        private val TAG = PhotoApp::class.java.simpleName
    }
}