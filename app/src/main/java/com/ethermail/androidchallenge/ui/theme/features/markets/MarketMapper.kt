package com.ethermail.androidchallenge.ui.theme.features.markets

import com.ethermail.androidchallenge.data.model.markets.MarketData
import com.ethermail.androidchallenge.util.DoubleUtil.formatToUSD
import com.ethermail.androidchallenge.util.LongUtil.toDateString
import com.ethermail.androidchallenge.util.StringUtil.toSafeDouble

object MarketMapper {
    fun MarketData.toUiModel(): MarketUiItem {
        return MarketUiItem(
            baseId = baseId ?: "N/A",
            exchangeId = exchangeId ?: "N/A",
            rank = rank ?: "N/A",
            priceUsd = priceUsd?.toSafeDouble()?.formatToUSD() ?: "N/A",
            lastUpdated = updated?.toDateString() ?: "N/A",
            volumeUsd24Hr = volumeUsd24Hr?.toSafeDouble()?.formatToUSD() ?: "N/A"
        )
    }
}