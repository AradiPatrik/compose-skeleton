package com.cardinalblue.moviedetails.impl.entry

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.cardinalblue.data.api.DataProvider
import com.cardinalblue.moviedetails.api.MovieDetailsFeatureEntry
import com.cardinalblue.moviedetails.api.MovieDetailsInput
import com.cardinalblue.moviedetails.impl.credits.screen.CreditsScreen
import com.cardinalblue.moviedetails.impl.di.DaggerMovieDetailsRootComponent
import com.cardinalblue.moviedetails.impl.di.MovieDetailsRootComponent
import com.cardinalblue.moviedetails.impl.directions.MovieDetailsDirections
import com.cardinalblue.moviedetails.impl.movie.screen.MovieScreen
import com.cardinalblue.navigation.BaseFeatureEntry
import com.cardinalblue.navigation.CompositionLocals
import com.cardinalblue.navigation.NavigationCommand
import com.cardinalblue.navigation.NavigationProvider
import com.cardinalblue.navigation.rememberScoped
import com.cardinalblue.platform.PlatformProvider
import javax.inject.Inject

/**
 * The entry point for the feature. Provides root component and navigation graph for the feature.
 */
class MovieDetailsFeatureEntryImpl @Inject constructor() : BaseFeatureEntry<MovieDetailsInput, MovieDetailsRootComponent>(
    rootRoute = "@movie-details",
    startRoute = MovieDetailsFeatureEntry.featureRoute
), MovieDetailsFeatureEntry {
    override fun NavGraphBuilder.buildNavigation(
        navController: NavHostController,
        navigate: NavHostController.(NavigationCommand) -> Unit
    ) {
        addNode(
            direction = MovieDetailsDirections.Movie,
            navController = navController,
            navigate = navigate,
            createVm = { _, input, component ->
                component.movieSubcomponentFactory.create().viewModelFactory.create(input)
            },
            content = { MovieScreen(it) }
        )

        addNode(
            direction = MovieDetailsDirections.Credits,
            navController = navController,
            navigate = navigate,
            createVm = { _, input, component ->
                component.creditsSubcomponentFactory.create().viewModelFactory.create(input)
            },
            content = { CreditsScreen(it) }
        )
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