package com.example.koinexticker.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.mvrx.*
import com.example.koinexticker.R
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.inr_ticker_fragment.*
import timber.log.Timber

class InrTickerFragment: BaseMvRxFragment() {

    private val inrTickerViewModel by fragmentViewModel(InrTickerViewModel::class)

    private val compositeDisposable = CompositeDisposable()

    private val adapter = TickerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.inr_ticker_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ticker_recycler_view.layoutManager = LinearLayoutManager(view.context)
        ticker_recycler_view.setHasFixedSize(true)
        ticker_recycler_view.adapter = adapter

        val tickerDisposable = inrTickerViewModel.getAllData()
        compositeDisposable.add(tickerDisposable)
    }

    override fun invalidate() {
        withState(inrTickerViewModel){
            val data = when(it.inrTicker){
                is Uninitialized -> listOf()
                is Loading -> listOf()
                is Success -> it.inrTicker.invoke()
                is Fail -> listOf()
            }
            if(data.isNotEmpty()) adapter.submitList(data)
        }

    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

}