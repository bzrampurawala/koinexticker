package com.example.koinexticker.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.koinexticker.KoinexTicker
import com.example.koinexticker.R
import com.example.koinexticker.model.InrTicker
import com.example.koinexticker.service.TickerService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var tickerService: TickerService

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        KoinexTicker.applicationComponent.inject(this)
        ticker_recycler_view.layoutManager = LinearLayoutManager(this)
        ticker_recycler_view.setHasFixedSize(true)
        val data = listOf(
            InrTicker(coin = "ETH",highestBid = "1234567", lowestAsk = "765432", lastPrice = "234532"),
            InrTicker(coin = "BTC",highestBid = "1234567", lowestAsk = "765432", lastPrice = "234532"),
            InrTicker(coin = "XRP",highestBid = "1234567", lowestAsk = "765432", lastPrice = "234532"),
            InrTicker(coin = "LTC",highestBid = "1234567", lowestAsk = "765432", lastPrice = "234532"),
            InrTicker(coin = "TRX",highestBid = "1234567", lowestAsk = "765432", lastPrice = "234532"),
            InrTicker(coin = "0X",highestBid = "1234567", lowestAsk = "765432", lastPrice = "234532"),
            InrTicker(coin = "BAT",highestBid = "1234567", lowestAsk = "765432", lastPrice = "234532"),
            InrTicker(coin = "NEO",highestBid = "1234567", lowestAsk = "765432", lastPrice = "234532"),
            InrTicker(coin = "BTT",highestBid = "1234567", lowestAsk = "765432", lastPrice = "234532")
        )
        val adapter = TickerAdapter(data)
        ticker_recycler_view.adapter = adapter

        val tickerDataDisposable = tickerService.getTickerData()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                Timber.i(it.string())
            },{
                Timber.e(it)
            })
        compositeDisposable.add(tickerDataDisposable)

    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}
