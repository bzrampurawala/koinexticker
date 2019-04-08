package com.example.koinexticker.di

import android.app.Application
import androidx.room.Room
import com.example.koinexticker.model.TickerDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule{

    @Provides
    @Singleton
    fun providesTickerDb(application: Application) =  Room.databaseBuilder(
        application,
        TickerDatabase::class.java, "greetings_database"
    ).build()

}