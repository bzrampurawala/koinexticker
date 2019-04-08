package com.example.koinexticker
import android.app.Application
import com.example.koinexticker.di.ApplicationComponent
import com.example.koinexticker.di.DaggerApplicationComponent
import timber.log.Timber

class KoinexTicker: Application(){
    companion object {
        lateinit var applicationComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            StethoUtils.init(this)
        }
        applicationComponent = DaggerApplicationComponent.builder()
            .application(this)
            .build()
        applicationComponent.inject(this)
    }
}