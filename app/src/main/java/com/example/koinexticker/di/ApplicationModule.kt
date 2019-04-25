package com.example.koinexticker.di

import com.example.koinexticker.TickerRepository
import com.example.koinexticker.model.InrTickerDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule{

    @Provides
    @Singleton
    fun providesTickerRepository(dao: InrTickerDao):TickerRepository = TickerRepository(dao)
}