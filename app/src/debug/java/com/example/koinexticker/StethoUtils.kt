package com.example.koinexticker

import android.app.Application
import com.facebook.stetho.Stetho

class StethoUtils {
    companion object {
        fun init(application: Application) = Stetho.initializeWithDefaults(application)
    }
}