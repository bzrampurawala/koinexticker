package com.example.koinexticker.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.mvrx.*
import com.example.koinexticker.KoinexTicker
import com.example.koinexticker.R
import com.example.koinexticker.model.InrTicker
import com.example.koinexticker.service.TickerJsonParser
import com.example.koinexticker.service.TickerService
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject



class MainActivity : AppCompatActivity() {

    private val inrTickerViewModel = InrTickerViewModel(InrTickerState(Uninitialized))

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
            InrTicker(coin = "ETH",highestBid = "1234567", lowestAsk = "765432", lastPrice = "234532", updatedTime = "234t432"),
            InrTicker(coin = "BTC",highestBid = "1234567", lowestAsk = "765432", lastPrice = "234532", updatedTime = "234t432"),
            InrTicker(coin = "XRP",highestBid = "1234567", lowestAsk = "765432", lastPrice = "234532", updatedTime = "234t432"),
            InrTicker(coin = "LTC",highestBid = "1234567", lowestAsk = "765432", lastPrice = "234532", updatedTime = "234t432"),
            InrTicker(coin = "TRX",highestBid = "1234567", lowestAsk = "765432", lastPrice = "234532", updatedTime = "234t432"),
            InrTicker(coin = "0X",highestBid = "1234567", lowestAsk = "765432", lastPrice = "234532", updatedTime = "234t432"),
            InrTicker(coin = "BAT",highestBid = "1234567", lowestAsk = "765432", lastPrice = "234532", updatedTime = "234t432"),
            InrTicker(coin = "NEO",highestBid = "1234567", lowestAsk = "765432", lastPrice = "234532", updatedTime = "234t432"),
            InrTicker(coin = "BTT",highestBid = "1234567", lowestAsk = "765432", lastPrice = "234532", updatedTime = "234t432")
        )

        withState(inrTickerViewModel){
            Timber.i(when(it.inrTicker){
                is Uninitialized -> "Uninitlialized"
                is Loading -> "Loading"
                is Success -> it.inrTicker()
                is Fail -> "Fail"
            }.toString())
        }

        val adapter = TickerAdapter(data)
        ticker_recycler_view.adapter = adapter
        val jsonParser = TickerJsonParser()
        val tickerDataDisposable = tickerService.getTickerData()
            .repeatWhen{it.delay(30, TimeUnit.SECONDS)}
            .retryWhen { it.delay(90, TimeUnit.SECONDS) }
            .map{
                val json = JSONObject(it.string())
                val stats = JSONObject(json["stats"].toString())
                val inr = stats["inr"]
                val ticker = jsonParser.fromJson(inr.toString())
                ticker
            }
            .flatMap{
                 Flowable.just(inrTickerViewModel.insertAll(it))
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                inrTickerViewModel.getAllData()
            },{ Timber.e(it) })

        compositeDisposable.add(tickerDataDisposable)

    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}
