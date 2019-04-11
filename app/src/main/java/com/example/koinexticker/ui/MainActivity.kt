package com.example.koinexticker.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.mvrx.*
import com.example.koinexticker.KoinexTicker
import com.example.koinexticker.R
import com.example.koinexticker.service.TickerJsonParser
import com.example.koinexticker.service.TickerService
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject



class MainActivity : AppCompatActivity() {


    @Inject
    lateinit var tickerService: TickerService

    private val inrTickerViewModel = InrTickerViewModel(InrTickerState(Uninitialized))

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        KoinexTicker.applicationComponent.inject(this)
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
            .subscribe({},{ Timber.e(it) })

        compositeDisposable.add(tickerDataDisposable)
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = InrTickerFragment()
        fragmentTransaction.add(R.id.main_fragment, fragment)
        fragmentTransaction.commit()

    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}
