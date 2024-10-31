package com.ethermail.androidchallenge.ui.theme.features.assets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

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
        when {
            isLoading -> LoadingScreen()
            error != null -> ErrorScreen(
                error = error,
                onTryAgain = { viewModel.loadAssets() }
            )

            else ->
                AssetsList(
                    Modifier.padding(innerPadding),
                    assets = assets,
                    onAssetClick = onAssetClick
                )
        }
    }


}

@Composable
fun ErrorScreen(error: String?, onTryAgain: () -> Unit) {
    Box(Modifier.fillMaxSize()) {
     Column(
            Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
     ) {
         Text(
             text = error ?: "An error occurred",
             color = MaterialTheme.colorScheme.error,
         )

         Spacer(modifier = Modifier.height(16.dp))

         Button(
             onClick = onTryAgain
         ) {
             Text("Try Again")
         }
     }
    }
}

@Composable
fun LoadingScreen() {
    Box(Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            Modifier.align(Alignment.Center),
        )
    }
}