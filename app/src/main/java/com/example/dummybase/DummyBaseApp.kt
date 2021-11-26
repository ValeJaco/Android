package com.example.dummybase

import android.app.Application
import android.content.Context
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.soloader.SoLoader
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DummyBaseApp : Application() {

    companion object {
        lateinit var appContext: Context
        lateinit var networkFlipperPlugin: NetworkFlipperPlugin
    }


    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext

        // initFlipper()
    }

    private fun initFlipper() {
        SoLoader.init(this, false)
        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
            val flipperClient = AndroidFlipperClient.getInstance(this)
            networkFlipperPlugin = NetworkFlipperPlugin()
            flipperClient.addPlugin(
                InspectorFlipperPlugin(
                    this@DummyBaseApp,
                    DescriptorMapping.withDefaults()
                )
            )
            flipperClient.addPlugin(networkFlipperPlugin)
            flipperClient.addPlugin( DatabasesFlipperPlugin(appContext));
            flipperClient.start()
        }
    }
}