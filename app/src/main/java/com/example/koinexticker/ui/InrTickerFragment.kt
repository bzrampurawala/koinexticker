package com.example.koinexticker.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.mvrx.*
import com.example.koinexticker.R
import com.example.koinexticker.model.InrTicker
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.inr_ticker_fragment.*
import timber.log.Timber

class InrTickerFragment: BaseMvRxFragment() {

    private val inrTickerViewModel by fragmentViewModel(InrTickerViewModel::class)

    private val compositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.inr_ticker_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        ticker_recycler_view.layoutManager = LinearLayoutManager(view.context)
        ticker_recycler_view.setHasFixedSize(true)

        val data = listOf(
            InrTicker(coin = "ETH",highestBid = "1234567", lowestAsk = "765432", lastPrice = "234532", updatedTime = "234t432"),
            InrTicker(coin = "BTC",highestBid = "1234567", lowestAsk = "765432", lastPrice = "234532", updatedTime = "234t432"),
            InrTicker(coin = "XRP",highestBid = "1234567", lowestAsk = "765432", lastPrice = "234532", updatedTime = "234t432"),
            InrTicker(coin = "LTC",highestBid = "1234567", lowestAsk = "765432", lastPrice = "234532", updatedTime = "234t432"),
            InrTicker(coin = "TRX",highestBid = "1234567", lowestAsk = "765432", lastPrice = "234532", updatedTime = "234t432"),
            InrTicker(coin = "0X",highestBid = "1234567", lowestAsk = "765432", lastPrice = "234532", updatedTime = "234t432"),
            InrTicker(coin = "BAT",highestBid = "1234567", lowestAsk = "765432", lastPrice = "234532", updatedTime = "234t432"),
            InrTicker(coin = "NEO",highestBid = "1234567", lowestAsk = "765432", lastPrice = "234532", updatedTime = "234t432"),
            InrTicker(coin = "BTT",highestBid = "1234567", lowestAsk = "765432", lastPrice = "234532", updatedTime = "234t432")
        )
        val tickerDisposable = inrTickerViewModel.getAllData()
        compositeDisposable.add(tickerDisposable)
        val adapter = TickerAdapter(data)
        ticker_recycler_view.adapter = adapter
        super.onViewCreated(view, savedInstanceState)
    }

    override fun invalidate() {
        withState(inrTickerViewModel){
            Timber.i(when(it.inrTicker){
                is Uninitialized -> listOf()
                is Loading -> listOf()
                is Success -> it.inrTicker.invoke()
                is Fail -> listOf()
            }.toString())
        }

    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

}