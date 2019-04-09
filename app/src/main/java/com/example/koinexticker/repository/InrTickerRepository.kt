package com.example.koinexticker.repository

import com.example.koinexticker.model.InrTicker
import com.example.koinexticker.model.InrTickerDao
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class InrTickerRepository @Inject constructor(private val inrTickerDao: InrTickerDao) {

    fun insertAll(tickerData: List<InrTicker>): Completable = Completable.create{ inrTickerDao.insertAll(tickerData) }

    fun insert(tickerData: InrTicker): Completable = Completable.create{ inrTickerDao.insert(tickerData) }

    fun delete(tickerData: InrTicker): Completable = Completable.create{ inrTickerDao.delete(tickerData) }

    fun getAll(): Flowable<List<InrTicker>> = inrTickerDao.getAll()

    fun getByCoin(coin: String): Flowable<InrTicker> = inrTickerDao.getByCoin(coin)

}