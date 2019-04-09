package com.example.koinexticker.service

import com.example.koinexticker.model.InrTicker
import com.squareup.moshi.*

class TickerJsonAdapter: JsonAdapter<List<InrTicker>>() {
    private val keys = JsonReader.Options.of("last_traded_price", "lowest_ask", "highest_bid")
    override fun toJson(writer: JsonWriter, value: List<InrTicker>?) {
        writer.beginArray()
        value?.map{
            writer.beginObject()
            writer.name("pair")
            writer.value("${it.coin}/${it.baseCoin}")
            writer.name("last_traded_price")
            writer.value(it.lastPrice)
            writer.name("lowest_ask")
            writer.value(it.lowestAsk)
            writer.name("highest_bid")
            writer.value(it.highestBid)
            writer.endObject()
        }
        writer.endArray()
    }

    @FromJson override fun fromJson(reader: JsonReader): List<InrTicker>? {

        val inrTicker = mutableListOf<InrTicker>()
        var lowestAsk = ""
        var lastPrice = ""
        var highestBid = ""
        reader.beginObject()
        while(reader.hasNext()){
            val coin = reader.nextName()
            reader.beginObject()
            while(reader.hasNext()){
                when(reader.selectName(keys)){
                    0 -> lastPrice = reader.nextString()
                    1 -> lowestAsk = reader.nextString()
                    2 -> highestBid = reader.nextString()
                    else -> reader.skipValue()
                }
            }
            val tickerData = InrTicker(coin = coin, highestBid = highestBid, lowestAsk = lowestAsk, lastPrice = lastPrice)
            inrTicker.add(tickerData)
            reader.endObject()
        }
        reader.endObject()
        return inrTicker
    }

}