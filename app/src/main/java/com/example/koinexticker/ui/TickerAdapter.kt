package com.example.koinexticker.ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.koinexticker.R
import com.example.koinexticker.model.InrTicker

import kotlinx.android.synthetic.main.ticker_row.view.*

class TickerAdapter: ListAdapter<InrTicker, TickerAdapter.TickerViewHolder>(TickerDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TickerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.ticker_row, parent, false)
        return TickerViewHolder(view)
    }

    override fun onBindViewHolder(holder: TickerViewHolder, position: Int) {
        val tickerData = getItem(position)
        holder.bind(tickerData)
    }

    inner class TickerViewHolder(view: View) : RecyclerView.ViewHolder(view){

        private val pairTextView = view.pair_text_view
        private val lastPriceTextView = view.last_price_text_view
        private val lowestAskTextView = view.lowest_ask_text_view
        private val highestBidTextView = view.highest_bid_text_view

        fun bind(tickerData: InrTicker){
            val pair = "${tickerData.coin}/${tickerData.baseCoin}"
            pairTextView.text = pair
            lastPriceTextView.text = tickerData.lastPrice
            lowestAskTextView.text = tickerData.lowestAsk
            highestBidTextView.text = tickerData.highestBid
        }
    }
    private fun playAnimation(view: View) {
        val fadeOut = ObjectAnimator.ofFloat(view, "alpha", 1f, .3f)
        fadeOut.duration = 400

        val fadeIn = ObjectAnimator.ofFloat(view, "alpha", .3f, 1f)
        fadeIn.duration = 400

        val animatorSet = AnimatorSet()
        animatorSet.play(fadeIn).after(fadeOut)
        animatorSet.start()
    }

}