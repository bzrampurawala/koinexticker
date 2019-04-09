package com.example.koinexticker.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.koinexticker.KoinexTicker
import com.example.koinexticker.R
import com.example.koinexticker.model.InrTicker
import com.example.koinexticker.TickerRepository
import com.example.koinexticker.service.TickerJsonParser
import com.example.koinexticker.service.TickerService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject



class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var tickerService: TickerService

    @Inject
    lateinit var repository: TickerRepository

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
        val jsonParser = TickerJsonParser()
        val tickerDataDisposable = tickerService.getTickerData()
            .repeatWhen{it.delay(30, TimeUnit.SECONDS)}
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                val json = JSONObject(it.string())
                val stats = JSONObject(json["stats"].toString())
                val inr = stats["inr"]
                val ticker = jsonParser.fromJson(inr.toString())
                if(ticker!=null) compositeDisposable.add(repository.insertAll(ticker)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                    Timber.i("added in the db")
                },{
                    Timber.e(it)
                }))
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
