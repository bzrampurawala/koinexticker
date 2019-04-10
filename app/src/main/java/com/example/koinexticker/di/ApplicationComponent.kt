package com.example.koinexticker.di

import android.app.Application
import android.content.Context
import com.example.koinexticker.TickerRepository
import com.example.koinexticker.ui.InrTickerViewModel
import com.example.koinexticker.ui.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [(ApplicationModule::class)]
)
@Singleton
interface ApplicationComponent {

    fun inject(koinexTicker: Application)
    fun inject(mainActivity: MainActivity)
    fun inject(viewModel: InrTickerViewModel)

    @Component.Builder
    interface Builder {

        fun build(): ApplicationComponent

        @BindsInstance
        fun application(context: Context): Builder
    }
}