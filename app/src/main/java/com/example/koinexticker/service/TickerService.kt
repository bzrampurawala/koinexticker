package com.example.koinexticker.service


import io.reactivex.Flowable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.http.GET
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import okhttp3.OkHttpClient




class TickerService {
    private val BASE_URL = "https://koinex.in"
    private val requestInterface: Ticker
    init {
        val logging = HttpLoggingInterceptor(CustomInterceptor())
        logging.level = HttpLoggingInterceptor.Level.BASIC
        logging.redactHeader("Authorization")
        logging.redactHeader("Cookie")
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        requestInterface = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(Ticker::class.java)
    }

    fun getTickerData() = requestInterface.getTicker()
}

interface Ticker{
    @GET("api/ticker")
    fun getTicker(): Flowable<ResponseBody>
}

class CustomInterceptor: HttpLoggingInterceptor.Logger{
    override fun log(message: String) {
        Timber.tag("OkHttpLogger").d(message)
    }

}