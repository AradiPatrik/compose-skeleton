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
import com.cardinalblue.navigation.rememberScoped
import com.cardinalblue.platform.PlatformProvider
import com.cardinalblue.skeleton.di.NavigationSubcomponent
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

        val navigationProvider: NavigationSubcomponent = rememberScoped(storeOwner = this, "navigation") {
            application.appProvider.navigationSubcomponentFactory.create(navController)
        }

        val appViewModel = injectedViewModel(this) {
            navigationProvider.appViewModel
        }

        CompositionLocalProvider(
            CompositionLocals.ofType<NavigationProvider>() provides navigationProvider,
            CompositionLocals.ofType<DataProvider>() provides application.appProvider,
            CompositionLocals.ofType<PlatformProvider>() provides application.appProvider,
            CompositionLocals.ofType<FeatureEntriesProvider>() provides application.appProvider,
            CompositionLocals.ofType<NavigationManager>() provides navigationProvider.navigationManager
        ) {
            App(navController, appViewModel)
        }
    }
}