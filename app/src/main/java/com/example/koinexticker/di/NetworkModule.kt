package com.example.koinexticker.di

import com.example.koinexticker.service.CustomInterceptor
import com.example.koinexticker.service.TickerService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    private val BASE_URL = "https://koinex.in"

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient{
        val logging = HttpLoggingInterceptor(CustomInterceptor())
        logging.level = HttpLoggingInterceptor.Level.BASIC
        logging.redactHeader("Authorization")
        logging.redactHeader("Cookie")
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun providesApiService(loggingClient: OkHttpClient): TickerService =
        Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(loggingClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(TickerService::class.java)

}