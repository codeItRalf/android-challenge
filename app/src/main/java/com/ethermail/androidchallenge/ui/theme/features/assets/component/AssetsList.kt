package com.ethermail.androidchallenge.ui.theme.features.assets.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ethermail.androidchallenge.ui.theme.features.assets.AssetUiItem
import com.ethermail.androidchallenge.ui.theme.features.assets.dummyAssets

@Composable
fun AssetsList(
    modifier: Modifier,
    assets: List<AssetUiItem>,
    onAssetClick: (String) -> Unit
   ) {
    LazyColumn(
        state = rememberLazyListState(),
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = PaddingValues(top = 4.dp),
    ) {
        items(count = assets.size, key = { index -> assets[index].symbol }) { index ->
            AssetView(asset = assets[index], onClick = { onAssetClick(assets[index].id) })
        }
    }
}


@Preview
@Composable
private fun PreviewAssetView() {
    AssetsList(
        modifier = Modifier.padding(16.dp),
        assets = dummyAssets,
        onAssetClick = {})
}