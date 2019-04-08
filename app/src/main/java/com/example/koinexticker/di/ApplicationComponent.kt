package com.example.koinexticker.di

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [(ApplicationModule::class)]
)
@Singleton
interface ApplicationComponent {

    fun inject(koinexTicker: Application)

    @Component.Builder
    interface Builder {

        fun build(): ApplicationComponent

        @BindsInstance
        fun application(context: Context): Builder
    }
}