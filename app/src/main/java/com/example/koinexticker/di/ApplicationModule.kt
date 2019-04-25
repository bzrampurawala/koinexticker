package com.example.koinexticker.di

import android.content.Context
import androidx.room.Room
import com.example.koinexticker.TickerRepository
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
    fun providesTickerDatabase(context: Context): TickerDatabase =  Room.databaseBuilder(
        context,
        TickerDatabase::class.java, "ticker_database"
    ).build()

    @Provides
    @Singleton
    fun providesInrTickerDao(tickerDatabase: TickerDatabase): InrTickerDao = tickerDatabase.inrTickerDao()

    @Provides
    @Singleton
    fun providesTickerRepository(dao: InrTickerDao):TickerRepository = TickerRepository(dao)
}