package com.example.koinexticker.service

import com.example.koinexticker.model.InrTicker
import com.squareup.moshi.*
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.http.GET
import okhttp3.ResponseBody
import org.json.JSONObject
import timber.log.Timber


class TickerService {
    private val BASE_URL = "https://koinex.in"
    private val requestInterface: Ticker
    init {
        requestInterface = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(Ticker::class.java)
    }

    fun getTickerData() = requestInterface.getTicker()
}

interface Ticker{
    @GET("api/ticker")
    fun getTicker(): Flowable<ResponseBody>
}

