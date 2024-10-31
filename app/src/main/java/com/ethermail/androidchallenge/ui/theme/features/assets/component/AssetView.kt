package com.ethermail.androidchallenge.ui.theme.features.assets.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ethermail.androidchallenge.ui.theme.features.assets.AssetUiItem

@Composable
fun AssetView(asset: AssetUiItem, onClick: () -> Unit) = Card(
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
