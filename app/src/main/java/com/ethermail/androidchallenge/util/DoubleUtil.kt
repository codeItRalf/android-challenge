package com.ethermail.androidchallenge.util

object DoubleUtil {
    fun Double.formatToUSD(): String {
        val currencyFormatter = java.text.NumberFormat.getCurrencyInstance(java.util.Locale.US).apply {
            maximumFractionDigits = 2
            minimumFractionDigits = 2
        }
        return currencyFormatter.format(this)
    }

}