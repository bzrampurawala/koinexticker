package com.example.koinexticker.ui

import com.airbnb.mvrx.MvRxState
import com.example.koinexticker.model.InrTicker

data class InrTickerState(val inrTicker: List<InrTicker>): MvRxState