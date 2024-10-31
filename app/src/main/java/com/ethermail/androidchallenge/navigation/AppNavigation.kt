package com.ethermail.androidchallenge.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ethermail.androidchallenge.ui.theme.features.assets.AssetsScreen
import com.ethermail.androidchallenge.ui.theme.features.markets.MarketScreen


sealed class Screen(val route: String) {
    object Asset : Screen("assets")
    object Market : Screen("market/{assetId}") {
        fun createRoute(assetId: String) = "market/$assetId"
    }
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Asset.route) {
        composable(Screen.Asset.route) {
            AssetsScreen(onAssetClick = { assetId ->
                navController.navigate(Screen.Market.createRoute(assetId))
            })
        }

        composable(route = Screen.Market.route) { backStackEntry ->
            val assetId = backStackEntry.arguments?.getString("assetId")
            requireNotNull(assetId) { "Asset ID cannot be null" }
            MarketScreen(assetId = assetId)
        }
    }
}