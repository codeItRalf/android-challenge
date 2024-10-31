package com.ethermail.androidchallenge.util


import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object LongUtil {

    fun Long.toDateString(): String {
        return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).apply {
            isLenient = false
        }.format(Date(this))
    }

}