package com.cardinalblue.skeleton

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptions
import androidx.navigation.compose.rememberNavController
import com.cardinalblue.data.api.DataProvider
import com.cardinalblue.featuredmovies.api.FeaturedMoviesFeatureEntry
import com.cardinalblue.moviesearch.api.MovieSearchFeatureEntry
import com.cardinalblue.navigation.CompositionLocals
import com.cardinalblue.navigation.FeatureEntriesProvider
import com.cardinalblue.navigation.NavigationProvider
import com.cardinalblue.navigation.find
import com.cardinalblue.navigation.injectedViewModel
import com.cardinalblue.navigation.rememberScoped
import com.cardinalblue.platform.PlatformProvider
import com.cardinalblue.profile.api.ProfileFeatureEntry
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
        val appViewModel = injectedViewModel(this) {
            application.appProvider.appViewModel
        }

        val routeDestinations = remember {
            listOf(
                FeaturedMoviesFeatureEntry.featureRoute,
                MovieSearchFeatureEntry.featureRoute,
                ProfileFeatureEntry.featureRoute
            )
        }
        val navController = rememberNavController()
        collectNavigationState().value?.let {
            val navOptions = if (it.destination in routeDestinations) {
                NavOptions.Builder()
                    .setPopUpTo(
                        destinationId = navController.graph.findStartDestination().id,
                        inclusive = false,
                        saveState = true
                    )
                    .setLaunchSingleTop(true)
                    .setRestoreState(true)
                    .build()
            } else {
                null
            }
            navController.navigate(it.destination, navOptions = navOptions)
        }

        CompositionLocalProvider(
            CompositionLocals.ofType<NavigationProvider>() provides application.appProvider,
            CompositionLocals.ofType<DataProvider>() provides application.appProvider,
            CompositionLocals.ofType<PlatformProvider>() provides application.appProvider,
            CompositionLocals.ofType<FeatureEntriesProvider>() provides application.appProvider
        ) {
            App(navController, appViewModel)
        }
    }

    @Composable
    private fun collectNavigationState() = application
        .appProvider
        .navigationManager
        .commands
        .collectAsState(initial = null)
}