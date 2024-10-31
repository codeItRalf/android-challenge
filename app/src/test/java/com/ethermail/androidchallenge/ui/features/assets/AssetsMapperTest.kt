package com.ethermail.androidchallenge.ui.features.assets

import com.ethermail.androidchallenge.data.model.assets.AssetData
import com.ethermail.androidchallenge.ui.theme.features.assets.AssetsMapper.toUiModel
import com.ethermail.androidchallenge.util.DoubleUtil.formatToUSD
import com.ethermail.androidchallenge.util.StringUtil.toSafeDouble
import org.junit.Assert.assertEquals
import org.junit.Test

class AssetsMapperTest {

    @Test
    fun assetDataToUiModel_allFieldsPresent_returnsCorrectUiModel() {
        val price = "45000.00"

        val assetData = AssetData(
            id = "1",
            symbol = "BTC",
            name = "Bitcoin",
            priceUsd = price,
            changePercent24Hr = null,
            explorer =  null,
            marketCapUsd = null,
            maxSupply =    null ,
            rank = null,
            supply = null,
            volumeUsd24Hr = null,
            vwap24Hr = null
        )

        val uiModel = assetData.toUiModel()

        assertEquals("1", uiModel.id)
        assertEquals("BTC", uiModel.symbol)
        assertEquals("Bitcoin", uiModel.name)
        assertEquals(price.toSafeDouble().formatToUSD(), uiModel.price)
    }

    @Test
    fun assetDataToUiModel_nullFields_returnsDefaultValues() {
        val assetData = AssetData(
            id = null,
            symbol = null,
            name = null,
            priceUsd = null,
            changePercent24Hr = null,
            explorer =  null,
            marketCapUsd = null,
            maxSupply =    null ,
            rank = null,
            supply = null,
            volumeUsd24Hr = null,
            vwap24Hr = null
        )

        val uiModel = assetData.toUiModel()

        assertEquals("", uiModel.id)
        assertEquals("N/A", uiModel.symbol)
        assertEquals("N/A", uiModel.name)
        assertEquals("N/A", uiModel.price)
    }
}