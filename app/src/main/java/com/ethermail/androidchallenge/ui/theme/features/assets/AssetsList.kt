package com.ethermail.androidchallenge.ui.theme.features.assets

import android.R.attr.onClick
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

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

@Composable
private fun AssetView(asset: AssetUiItem, onClick: () -> Unit) = Card(
    shape = RoundedCornerShape(10),
    modifier = Modifier.fillMaxWidth(),
    onClick = { onClick() }
) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = asset.name)
        Row {
            Text(text = asset.symbol, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = asset.price)
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