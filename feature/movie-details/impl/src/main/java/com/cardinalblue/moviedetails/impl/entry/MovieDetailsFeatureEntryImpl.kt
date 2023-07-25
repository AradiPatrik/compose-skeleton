package com.cardinalblue.moviedetails.impl.entry

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.cardinalblue.data.api.DataProvider
import com.cardinalblue.moviedetails.api.MovieDetailsFeatureEntry
import com.cardinalblue.moviedetails.api.MovieDetailsInput
import com.cardinalblue.moviedetails.impl.credits.di.CreditsSubcomponent
import com.cardinalblue.moviedetails.impl.credits.screen.CreditsScreen
import com.cardinalblue.moviedetails.impl.di.DaggerMovieDetailsRootComponent
import com.cardinalblue.moviedetails.impl.di.MovieDetailsRootComponent
import com.cardinalblue.moviedetails.impl.directions.MovieDetailsDirections
import com.cardinalblue.moviedetails.impl.movie.di.MovieSubcomponent
import com.cardinalblue.moviedetails.impl.movie.screen.MovieScreen
import com.cardinalblue.navigation.BaseFeatureEntry
import com.cardinalblue.navigation.CompositionLocals
import com.cardinalblue.navigation.FeatureGraphBuilderScope
import com.cardinalblue.navigation.rememberScoped
import com.cardinalblue.platform.PlatformProvider
import javax.inject.Inject

/**
 * The entry point for the feature. Provides root component and navigation graph for the feature.
 */
class MovieDetailsFeatureEntryImpl @Inject constructor()
    : BaseFeatureEntry<MovieDetailsInput, MovieDetailsRootComponent>(
    rootRoute = "@movie-details",
    startRoute = MovieDetailsFeatureEntry.featureRoute
), MovieDetailsFeatureEntry {
    override fun FeatureGraphBuilderScope.buildNavigation() {
        addNode(
            direction = MovieDetailsDirections.Movie,
            subcomponentFactory = MovieDetailsRootComponent::movieSubcomponentFactory,
            viewModelFactory = MovieSubcomponent::viewModelFactory,
            content = { MovieScreen(it) }
        )

        addNode(
            direction = MovieDetailsDirections.Credits,
            subcomponentFactory = MovieDetailsRootComponent::creditsSubcomponentFactory,
            viewModelFactory = CreditsSubcomponent::viewModelFactory,
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
        return rememberScoped(rootEntry) {
            DaggerMovieDetailsRootComponent.factory()
                .create(
                    currentDataProvider,
                    currentPlatformProvider,
                    navController,
                )
        }
    }
}