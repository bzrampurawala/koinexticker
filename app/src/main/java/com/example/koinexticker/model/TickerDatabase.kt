package com.example.koinexticker.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [InrTicker::class], version = 1)
abstract  class TickerDatabase: RoomDatabase() {
    abstract fun inrTickerDao(): InrTickerDao
}