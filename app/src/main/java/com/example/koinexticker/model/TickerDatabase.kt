package com.example.koinexticker.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Ticker::class], version = 1)
abstract  class TickerDatabase: RoomDatabase() {
    abstract fun tickerDao(): TickerDao
}