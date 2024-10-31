package com.ethermail.androidchallenge.util

object StringUtil {
    fun String.toSafeDouble(): Double {
        return try {
            this.toDouble()
        } catch (e: NumberFormatException) {
            0.0
        }
    }

    fun String.capitalizeFirstLetter(): String = this.substring(0, 1).uppercase() + this.substring(1)
}