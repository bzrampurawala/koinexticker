package com.example.koinexticker.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class InrTicker(
    @PrimaryKey val coin: String,
    @field:Json(name="highest_bid") @ColumnInfo(name = "highest_bid") val highestBid: String,
    @field:Json(name="lowest_ask") @ColumnInfo(name = "lowest_ask") val lowestAsk: String,
    @field:Json(name="last_traded_price") @ColumnInfo(name = "last_price") val lastPrice: String,
    @ColumnInfo(name = "base_coin") val baseCoin: String = "INR"
)