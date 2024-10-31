package com.ethermail.androidchallenge.ui.features.markets

import com.ethermail.androidchallenge.data.model.markets.MarketData
import com.ethermail.androidchallenge.ui.theme.features.markets.MarketMapper.toUiModel
import com.ethermail.androidchallenge.util.DoubleUtil.formatToUSD
import com.ethermail.androidchallenge.util.LongUtil.toDateString
import com.ethermail.androidchallenge.util.StringUtil.toSafeDouble
import org.junit.Assert.assertEquals
import org.junit.Test

class MarketMapperTest {

    @Test
    fun marketDataToUiModel_allFieldsPresent_returnsCorrectUiModel() {

        val updated = 1627847282000
        val priceUsd = "45000.00"
        val volumeUsd24Hr = "1000000000.00"
        val marketData = MarketData(
            baseId = "BTC",
            exchangeId = "Binance",
            rank = "1",
            priceUsd = priceUsd,
            updated = 1627847282000,
            volumeUsd24Hr = volumeUsd24Hr,
            baseSymbol = "BTC",
            percentExchangeVolume = "10",
            priceQuote = "45000.00",
            quoteId = "usd",
            quoteSymbol = "USD",
            tradesCount24Hr = "1000",
        )

        val uiModel = marketData.toUiModel()

        assertEquals("BTC", uiModel.baseId)
        assertEquals("Binance", uiModel.exchangeId)
        assertEquals("1", uiModel.rank)
        assertEquals(priceUsd.toSafeDouble().formatToUSD(), uiModel.priceUsd)
        assertEquals(updated.toDateString(), uiModel.lastUpdated)
        assertEquals(volumeUsd24Hr.toSafeDouble().formatToUSD(), uiModel.volumeUsd24Hr)


    }

    @Test
    fun marketDataToUiModel_nullFields_returnsDefaultValues() {
        val marketData = MarketData(
            baseId = null,
            exchangeId = null,
            rank = null,
            priceUsd = null,
            updated = null,
            volumeUsd24Hr = null,
            baseSymbol = null,
            percentExchangeVolume = null,
            priceQuote = null,
            quoteId = null,
            quoteSymbol = null,
            tradesCount24Hr = null,
        )

        val uiModel = marketData.toUiModel()

        assertEquals("N/A", uiModel.baseId)
        assertEquals("N/A", uiModel.exchangeId)
        assertEquals("N/A", uiModel.rank)
        assertEquals("N/A", uiModel.priceUsd)
        assertEquals("N/A", uiModel.lastUpdated)
        assertEquals("N/A", uiModel.volumeUsd24Hr)
    }


}