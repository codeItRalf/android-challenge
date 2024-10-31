package com.ethermail.androidchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.ethermail.androidchallenge.navigation.AppNavigation
import com.ethermail.androidchallenge.ui.theme.features.assets.AssetsScreen
import com.ethermail.androidchallenge.ui.theme.features.assets.dummyAssets
import com.ethermail.androidchallenge.ui.theme.AndroidChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidChallengeTheme {
                val navController = rememberNavController()
                AppNavigation(navController = navController)
            }
        }
    }
}
