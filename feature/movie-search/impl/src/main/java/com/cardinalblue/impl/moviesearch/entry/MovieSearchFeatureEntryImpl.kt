package com.cardinalblue.impl.moviesearch.entry

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.cardinalblue.data.api.DataProvider
import com.cardinalblue.moviesearch.api.MovieSearchFeatureEntry
import com.cardinalblue.impl.moviesearch.di.DaggerMovieSearchRootComponent
import com.cardinalblue.impl.moviesearch.di.MovieSearchRootComponent
import com.cardinalblue.impl.moviesearch.search.screen.SearchScreen
import com.cardinalblue.navigation.CompositionLocals
import com.cardinalblue.navigation.RootComponentHolder
import com.cardinalblue.navigation.injectedViewModel
import com.cardinalblue.navigation.rememberScoped
import com.cardinalblue.platform.PlatformProvider
import javax.inject.Inject

/**
 * The entry point for the feature. Provides root component and navigation graph for the feature.
 */
class MovieSearchFeatureEntryImpl @Inject constructor() : MovieSearchFeatureEntry(),
    RootComponentHolder<MovieSearchRootComponent> {
    override val rootRoute: String
        get() = "@movie-search"

    override fun NavGraphBuilder.navigation(
        navController: NavHostController,
    ) {
        navigation(startDestination = featureRoute, route = rootRoute) {
            composable(featureRoute, arguments) { backstackEntry ->
                val rootComponent = rootComponent(backstackEntry, navController)

                val viewModel = injectedViewModel(backstackEntry) {
                    rootComponent.searchSubcomponentFactory.create().viewModel
                }

                SearchScreen(viewModel = viewModel)
            }
        }
    }

    @Composable
    override fun provideRootComponent(
        rootEntry: NavBackStackEntry,
        navController: NavController,
        arguments: Bundle?
    ): MovieSearchRootComponent {
        val currentDataProvider = CompositionLocals.current<DataProvider>()
        val currentPlatformProvider = CompositionLocals.current<PlatformProvider>()
        return rememberScoped(rootEntry) {
            DaggerMovieSearchRootComponent.builder()
                .dataProvider(currentDataProvider)
                .platformProvider(currentPlatformProvider)
                .build()
        }
    }
}