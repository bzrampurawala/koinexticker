package com.example.koinexticker.di

import android.app.Application
import androidx.room.Room
import com.example.koinexticker.model.InrTickerDao
import com.example.koinexticker.model.TickerDatabase
import com.example.koinexticker.service.TickerService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule{

    @Provides
    @Singleton
    fun providesTickerDatabase(application: Application): TickerDatabase =  Room.databaseBuilder(
        application,
        TickerDatabase::class.java, "greetings_database"
    ).build()

    @Provides
    @Singleton
    fun providesInrTickerDao(tickerDatabase: TickerDatabase): InrTickerDao = tickerDatabase.inrTickerDao()

    @Provides
    @Singleton
    fun providesTickerApiService(): TickerService = TickerService()
}