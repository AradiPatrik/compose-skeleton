package com.cardinalblue.moviedetails.impl.entry

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.cardinalblue.data.api.DataProvider
import com.cardinalblue.moviedetails.api.MovieDetailsFeatureEntry
import com.cardinalblue.moviedetails.api.MovieDetailsInput
import com.cardinalblue.moviedetails.impl.credits.screen.CreditsScreen
import com.cardinalblue.moviedetails.impl.di.DaggerMovieDetailsRootComponent
import com.cardinalblue.moviedetails.impl.di.MovieDetailsRootComponent
import com.cardinalblue.moviedetails.impl.directions.MovieDetailsDirections
import com.cardinalblue.moviedetails.impl.movie.screen.MovieScreen
import com.cardinalblue.navigation.CompositionLocals
import com.cardinalblue.navigation.RootComponentHolder
import com.cardinalblue.navigation.injectedViewModel
import com.cardinalblue.navigation.rememberScoped
import com.cardinalblue.platform.PlatformProvider
import com.cardinalblue.navigation.NavigationProvider
import com.cardinalblue.navigation.composable
import com.cardinalblue.navigation.getInput
import javax.inject.Inject

/**
 * The entry point for the feature. Provides root component and navigation graph for the feature.
 */
class MovieDetailsFeatureEntryImpl @Inject constructor() : MovieDetailsFeatureEntry(),
    RootComponentHolder<MovieDetailsRootComponent> {
    override val rootRoute: String
        get() = "@movie-details"

    override fun NavGraphBuilder.navigation(
        navController: NavHostController,
    ) {
        navigation(startDestination = featureRoute, route = rootRoute) {
            composable(MovieDetailsDirections.Movie) { backstackEntry ->
                val rootComponent = rootComponent(backstackEntry, navController)

                val viewModel = injectedViewModel(backstackEntry) {
                    rootComponent.movieSubcomponentFactory.create().viewModelFactory.create(
                        backstackEntry.getInput(MovieDetailsInput)
                    )
                }

                MovieScreen(viewModel = viewModel)
            }
            composable(MovieDetailsDirections.Credits) { backStackEntry ->
                val rootComponent = rootComponent(backStackEntry, navController)

                val viewModel = injectedViewModel(backStackEntry) {
                    rootComponent.creditsSubcomponentFactory.create().viewModelFactory.create(
                        backStackEntry.getInput(MovieDetailsInput)
                    )
                }

                CreditsScreen(viewModel)
            }
        }
    }

    @Composable
    override fun provideRootComponent(
        rootEntry: NavBackStackEntry,
        navController: NavController,
        arguments: Bundle?
    ): MovieDetailsRootComponent {
        val currentDataProvider = CompositionLocals.current<DataProvider>()
        val currentPlatformProvider = CompositionLocals.current<PlatformProvider>()
        val navigationProvider = CompositionLocals.current<NavigationProvider>()
        return rememberScoped(rootEntry) {
            DaggerMovieDetailsRootComponent.factory()
                .create(
                    currentDataProvider,
                    currentPlatformProvider,
                    navigationProvider,
                )
        }
    }
}