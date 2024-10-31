package com.ethermail.androidchallenge.ui.theme.features.assets

import com.ethermail.androidchallenge.data.model.assets.AssetData

object AssetsMapper {
    fun AssetData.toUiModel(): AssetUiItem {
        return AssetUiItem(
            symbol = symbol ?: "N/A",
            name = name ?: "N/A",
            price = priceUsd ?: "N/A"
        )
    }
}