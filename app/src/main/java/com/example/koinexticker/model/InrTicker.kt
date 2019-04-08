package com.example.koinexticker.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class InrTicker(
    @PrimaryKey val coin: String,
    @ColumnInfo(name = "highest_bid") val highestBid: String,
    @ColumnInfo(name = "lowest_ask") val lowestAsk: String,
    @ColumnInfo(name = "last_price") val lastPrice: String,
    @ColumnInfo(name = "base_coin") val baseCoin: String = "INR"
)