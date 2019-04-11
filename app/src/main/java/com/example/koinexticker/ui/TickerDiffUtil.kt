package com.example.koinexticker.ui

import androidx.recyclerview.widget.DiffUtil
import com.example.koinexticker.model.InrTicker

class TickerDiffUtil: DiffUtil.ItemCallback<InrTicker>() {

    override fun areItemsTheSame(oldItem: InrTicker, newItem: InrTicker): Boolean {
        return oldItem.coin == newItem.coin
    }

    override fun areContentsTheSame(oldItem: InrTicker, newItem: InrTicker): Boolean {
        return oldItem.coin == newItem.coin &&
                oldItem.lowestAsk == newItem.lowestAsk &&
                oldItem.highestBid == newItem.highestBid &&
                oldItem.lastPrice == newItem.lastPrice
    }
}