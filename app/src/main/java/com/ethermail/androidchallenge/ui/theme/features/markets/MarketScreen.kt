package com.ethermail.androidchallenge.ui.theme.features.markets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ethermail.androidchallenge.ui.theme.features.common.StateHandler
import com.ethermail.androidchallenge.ui.theme.features.markets.component.MarketBody
import com.ethermail.androidchallenge.util.StringUtil.capitalizeFirstLetter


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarketScreen(assetId: String, viewModel: MarketViewModel = hiltViewModel(), navController: NavController) {
    val market by viewModel.market.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()


    LaunchedEffect(true) {
        viewModel.loadMarketWithHighestVolume(assetId)
    }

    Scaffold (
        topBar = {
            TopAppBar(title = {
                Column(
                ) {
                    Text(text = "Highest Volume(24H)")
                    Text(text = assetId.capitalizeFirstLetter(), style = MaterialTheme.typography.labelLarge)
                }
            },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                })
        }
    ) { innerPadding ->

        StateHandler(isLoading = isLoading || market == null,
            error = error,
            onRetry = { viewModel.loadMarketWithHighestVolume(assetId) }) {
            MarketBody(
                Modifier.padding(innerPadding),
                market = market!!
            )
        }

    }


}