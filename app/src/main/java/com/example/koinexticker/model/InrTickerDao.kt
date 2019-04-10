package com.example.koinexticker.model

import androidx.room.*
import io.reactivex.Flowable
import io.reactivex.Observable

@Dao
interface InrTickerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(inrTicker: InrTicker)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(inrTicker: List<InrTicker>)

    @Delete
    fun delete(inrTicker: InrTicker)

    @Query("SELECT * FROM inrTicker")
    fun getAll(): Observable<MutableList<InrTicker>>

    @Query("SELECT * from inrTicker where coin like :coin")
    fun getByCoin(coin: String): Flowable<InrTicker>
}