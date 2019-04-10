package com.example.koinexticker.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

class Utils{
    companion object {
        @SuppressLint("SimpleDateFormat")
        fun getTimeStamp(): String {
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
            val millis = System.currentTimeMillis()
            val date = Date(millis)
            return sdf.format(date)
        }
    }
}