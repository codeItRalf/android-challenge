package com.ethermail.androidchallenge.ui.theme.features.common

import androidx.compose.runtime.Composable

@Composable
fun StateHandler(
    isLoading: Boolean,
    error: String?,
    onRetry: () -> Unit,
    content: @Composable () -> Unit
) {
    when {
        error != null -> ErrorScreen(
            error = error,
            onTryAgain = onRetry
        )
        isLoading -> LoadingScreen()

        else -> content()
    }
}