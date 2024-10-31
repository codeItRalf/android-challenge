package com.ethermail.androidchallenge.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ethermail.androidchallenge.ui.theme.features.assets.AssetsScreen


sealed class Screen(val route: String) {
    object AssetsScreen : Screen("assets")
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.AssetsScreen.route) {
        composable(Screen.AssetsScreen.route) {
            AssetsScreen()
        }
    }
}