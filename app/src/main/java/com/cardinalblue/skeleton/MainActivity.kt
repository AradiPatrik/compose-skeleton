package com.cardinalblue.skeleton

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.rememberNavController
import com.cardinalblue.data.api.DataProvider
import com.cardinalblue.navigation.CompositionLocals
import com.cardinalblue.navigation.FeatureEntriesProvider
import com.cardinalblue.navigation.NavigationManager
import com.cardinalblue.navigation.NavigationManagerImpl
import com.cardinalblue.navigation.NavigationProvider
import com.cardinalblue.navigation.injectedViewModel
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
        LaunchedEffect(key1 = navController) {
            getNavigationManager().navController = navController
        }

        val appViewModel = injectedViewModel(this) {
            application.appProvider.appViewModel
        }

        CompositionLocalProvider(
            CompositionLocals.ofType<NavigationProvider>() provides application.appProvider,
            CompositionLocals.ofType<DataProvider>() provides application.appProvider,
            CompositionLocals.ofType<PlatformProvider>() provides application.appProvider,
            CompositionLocals.ofType<FeatureEntriesProvider>() provides application.appProvider,
            CompositionLocals.ofType<NavigationManager>() provides getNavigationManager()
        ) {
            App(navController, appViewModel)
        }
    }

    private fun getNavigationManager() = (application
        .appProvider
        .navigationManager as NavigationManagerImpl)
}