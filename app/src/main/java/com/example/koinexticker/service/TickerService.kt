package com.example.koinexticker.service


import io.reactivex.Flowable
import retrofit2.http.GET
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber


interface TickerService{
    @GET("api/ticker")
    fun getData(): Flowable<ResponseBody>
}

class CustomInterceptor: HttpLoggingInterceptor.Logger{
    override fun log(message: String) {
        Timber.tag("OkHttpLogger").d(message)
    }
}