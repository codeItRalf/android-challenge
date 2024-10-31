package com.ethermail.androidchallenge.util

object StringUtil {
    fun String.toSafeDouble(): Double {
        return try {
            this.toDouble()
        } catch (e: NumberFormatException) {
            0.0
        }
    }
}