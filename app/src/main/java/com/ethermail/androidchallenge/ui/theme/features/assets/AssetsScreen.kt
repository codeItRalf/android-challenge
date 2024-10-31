package com.ethermail.androidchallenge.ui.theme.features.assets

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AssetsScreen (
    viewModel: AssetsViewModel = hiltViewModel(),
){

    val assets by viewModel.assets.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    LaunchedEffect (true){
        viewModel.loadAssets()
    }

    Scaffold { innerPadding ->
        AssetsList(
            Modifier.padding(innerPadding),
            assets = assets,
        )
    }
}