package com.cardinalblue.featuredmovies.impl.entry

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.cardinalblue.data.api.DataProvider
import com.cardinalblue.featuredmovies.api.FeaturedMoviesFeatureEntry
import com.cardinalblue.featuredmovies.impl.di.DaggerFeaturedMoviesRootComponent
import com.cardinalblue.featuredmovies.impl.di.FeaturedMoviesRootComponent
import com.cardinalblue.featuredmovies.impl.featuredmovieslist.screen.FeaturedMoviesListScreen
import com.cardinalblue.navigation.CompositionLocals
import com.cardinalblue.navigation.RootComponentHolder
import com.cardinalblue.navigation.injectedViewModel
import com.cardinalblue.navigation.rememberScoped
import com.cardinalblue.platform.PlatformProvider
import javax.inject.Inject

/**
 * The entry point for the feature. Provides root component and navigation graph for the feature.
 */
class FeaturedMoviesFeatureEntryImpl @Inject constructor() : FeaturedMoviesFeatureEntry(),
    RootComponentHolder<FeaturedMoviesRootComponent> {
    override val rootRoute: String
        get() = "@featured-movies"

    override fun NavGraphBuilder.navigation(
        navController: NavHostController,
    ) {
        navigation(startDestination = featureRoute, route = rootRoute) {
            composable(featureRoute, arguments) { backstackEntry ->
                val rootComponent = rootComponent(backstackEntry, navController)

                val viewModel = injectedViewModel(backstackEntry) {
                    rootComponent.featuredMoviesListSubcomponentFactory.create().viewModel
                }

                FeaturedMoviesListScreen(viewModel = viewModel)
            }
        }
    }

    @Composable
    override fun provideRootComponent(
        rootEntry: NavBackStackEntry,
        navController: NavController,
        arguments: Bundle?
    ): FeaturedMoviesRootComponent {
        val currentDataProvider = CompositionLocals.current<DataProvider>()
        val currentPlatformProvider = CompositionLocals.current<PlatformProvider>()
        return rememberScoped(rootEntry) {
            DaggerFeaturedMoviesRootComponent.builder()
                .dataProvider(currentDataProvider)
                .platformProvider(currentPlatformProvider)
                .build()
        }
    }
}