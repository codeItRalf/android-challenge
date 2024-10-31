package com.ethermail.androidchallenge.ui.theme.features.markets

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun MarketScreen(assetId: String, viewModel: MarketViewModel = hiltViewModel()) {
    val market by viewModel.market.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()


    LaunchedEffect(true) {
        viewModel.loadMarketWithHighestVolume(assetId)
    }

    Scaffold { innerPadding ->
        when{
            market != null -> MarketBody(
                Modifier.padding(innerPadding),
                market = market!!
            )
        }
    }


}