package com.example.koinexticker.ui

import com.airbnb.mvrx.BaseMvRxViewModel
import com.example.koinexticker.KoinexTicker
import com.example.koinexticker.TickerRepository
import com.example.koinexticker.model.InrTicker
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class InrTickerViewModel(initialState: InrTickerState) : BaseMvRxViewModel<InrTickerState>(initialState, debugMode = true){
    @Inject
    lateinit var repository: TickerRepository

    init {
        KoinexTicker.applicationComponent.inject(this)
    }

    fun insertAll(inrTicker: List<InrTicker>) = repository.insertAll(inrTicker)
    fun getAllData() = repository.getAll()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .execute {
            copy(inrTicker = it)
        }
}