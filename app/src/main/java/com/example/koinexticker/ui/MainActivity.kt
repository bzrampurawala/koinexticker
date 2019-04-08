package com.example.koinexticker.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.koinexticker.R
import com.example.koinexticker.model.InrTicker
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ticker_recycler_view.layoutManager = LinearLayoutManager(this)
        ticker_recycler_view.setHasFixedSize(true)
        val data = listOf(
            InrTicker(coin = "ETH",highestBid = "1234567", lowestAsk = "765432", lastPrice = "234532"),
            InrTicker(coin = "BTC",highestBid = "1234567", lowestAsk = "765432", lastPrice = "234532"),
            InrTicker(coin = "XRP",highestBid = "1234567", lowestAsk = "765432", lastPrice = "234532"),
            InrTicker(coin = "LTC",highestBid = "1234567", lowestAsk = "765432", lastPrice = "234532")
        )
        val adapter = TickerAdapter(data)
        ticker_recycler_view.adapter = adapter
    }
}
