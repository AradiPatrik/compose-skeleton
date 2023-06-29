package com.cardinalblue.impl.moviesearch.entry

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.cardinalblue.data.api.DataProvider
import com.cardinalblue.impl.moviesearch.di.DaggerMovieSearchRootComponent
import com.cardinalblue.impl.moviesearch.di.MovieSearchRootComponent
import com.cardinalblue.impl.moviesearch.search.di.SearchSubcomponent
import com.cardinalblue.impl.moviesearch.search.screen.SearchScreen
import com.cardinalblue.moviesearch.api.MovieSearchFeatureEntry
import com.cardinalblue.navigation.BaseFeatureEntry
import com.cardinalblue.navigation.CompositionLocals
import com.cardinalblue.navigation.EmptyInput
import com.cardinalblue.navigation.FeatureGraphBuilderScope
import com.cardinalblue.navigation.rememberScoped
import com.cardinalblue.platform.PlatformProvider
import javax.inject.Inject

/**
 * The entry point for the feature. Provides root component and navigation graph for the feature.
 */
class MovieSearchFeatureEntryImpl @Inject constructor() :
    BaseFeatureEntry<EmptyInput, MovieSearchRootComponent>(
        rootRoute = "@movie-search",
        startRoute = MovieSearchFeatureEntry.featureRoute
    ), MovieSearchFeatureEntry {

    override fun FeatureGraphBuilderScope.buildNavigation() {
        addNode(
            direction = MovieSearchFeatureEntry.Direction,
            subcomponentFactory = MovieSearchRootComponent::searchSubcomponentFactory,
            viewModelFactory = SearchSubcomponent::viewModelFactory,
            content = { SearchScreen(it) }
        )
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
            DaggerMovieSearchRootComponent.factory()
                .create(
                    dataProvider = currentDataProvider,
                    platformProvider = currentPlatformProvider,
                    navController = navController
                )
        }
    }
}