package com.cardinalblue.skeleton

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.rememberNavController
import com.cardinalblue.api.DataProvider
import com.cardinalblue.navigation.CompositionLocals
import com.cardinalblue.navigation.FeatureEntriesProvider
import com.cardinalblue.platform.PlatformProvider
import com.cardinalblue.skeleton.navigation.App
import com.cardinalblue.theme.SkeletonTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SkeletonTheme {
                AppWithInitializedProviders()
            }
        }
    }

    @Composable
    private fun AppWithInitializedProviders() {
        val navController = rememberNavController()


        CompositionLocalProvider(
            CompositionLocals.ofType<DataProvider>() provides application.appProvider,
            CompositionLocals.ofType<PlatformProvider>() provides application.appProvider,
            CompositionLocals.ofType<FeatureEntriesProvider>() provides application.appProvider
        ) {
            App(navController)
        }
    }
}