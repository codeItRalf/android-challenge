package com.ethermail.androidchallenge.ui.theme.features.assets

import com.ethermail.androidchallenge.data.model.assets.AssetData
import com.ethermail.androidchallenge.util.DoubleUtil.formatToUSD
import com.ethermail.androidchallenge.util.StringUtil.toSafeDouble

object AssetsMapper {
    fun AssetData.toUiModel(): AssetUiItem {
        return AssetUiItem(
            id = id ?: "",
            symbol = symbol ?: "N/A",
            name = name ?: "N/A",
            price = priceUsd?.toSafeDouble()?.formatToUSD() ?: "N/A",
        )
    }
}