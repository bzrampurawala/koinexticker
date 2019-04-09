package com.example.koinexticker.service

import com.example.koinexticker.model.InrTicker
import com.squareup.moshi.Json
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import com.squareup.moshi.Moshi
import okhttp3.ResponseBody
import retrofit2.create


class TickerService {
    private val BASE_URL = "https://koinex.in"
    private val moshi: Moshi
    private val requestInterface: Ticker
    init {
        moshi = Moshi.Builder().build()
        requestInterface = Retrofit.Builder()
            .baseUrl(BASE_URL)
//            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(Ticker::class.java)
    }

    fun getTickerData(): Observable<ResponseBody> = requestInterface.getTicker()
}

interface Ticker{
    @GET("api/ticker")
    fun getTicker(): Observable<ResponseBody>
}

data class TickerEntity(
    @field:Json(name="stats") val stats: Stats
)

data class Stats(
    @field:Json(name="inr") val inr: InrTicker
)

