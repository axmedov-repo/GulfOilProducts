package com.gulfoil.pdsapp.app

import android.app.Application
import com.gulfoil.pdsapp.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {
    companion object {
        const val IS_RELEASE: Boolean = false
        const val IS_DIFFIE_HELLMAN_ALGO_ENABLED: Boolean = true
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
