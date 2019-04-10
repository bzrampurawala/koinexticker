package com.example.koinexticker

import com.example.koinexticker.model.InrTicker
import com.example.koinexticker.model.InrTickerDao
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Inject

class TickerRepository (private val inrTickerDao: InrTickerDao) {

    fun insertAll(tickerData: List<InrTicker>) = inrTickerDao.insertAll(tickerData)

    fun insert(tickerData: InrTicker): Completable = Completable.create{ inrTickerDao.insert(tickerData) }

    fun delete(tickerData: InrTicker): Completable = Completable.create{ inrTickerDao.delete(tickerData) }

    fun getAll(): Observable<List<InrTicker>> = inrTickerDao.getAll()

    fun getByCoin(coin: String): Flowable<InrTicker> = inrTickerDao.getByCoin(coin)

}