package com.ethermail.androidchallenge.ui.theme.features.assets

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ethermail.androidchallenge.ui.theme.features.assets.component.AssetsList
import com.ethermail.androidchallenge.ui.theme.features.common.StateHandler

@Composable
fun AssetsScreen(
    viewModel: AssetsViewModel = hiltViewModel(),
    onAssetClick: (String) -> Unit
) {

    val assets by viewModel.assets.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    LaunchedEffect(true) {
        viewModel.loadAssets()
    }

    Scaffold { innerPadding ->

        StateHandler(isLoading = isLoading,
            error = error,
            onRetry = { viewModel.loadAssets() }) {
            AssetsList(
                Modifier.padding(innerPadding),
                assets = assets,
                onAssetClick = onAssetClick
            )

        }



    }
}





