package com.cardinalblue.impl.moviesearch.entry

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.cardinalblue.navigation.BaseFeatureEntry
import com.cardinalblue.navigation.CompositionLocals
import com.cardinalblue.navigation.EmptyInput
import com.cardinalblue.navigation.NavigationCommand
import com.cardinalblue.navigation.NavigationProvider
import com.cardinalblue.navigation.RootComponentHolder
import com.cardinalblue.navigation.injectedViewModel
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

    override fun NavGraphBuilder.buildNavigation(
        navController: NavHostController,
        navigate: NavHostController.(NavigationCommand) -> Unit
    ) {
        addNode(
            direction = MovieSearchFeatureEntry.Direction,
            navController = navController,
            navigate = navigate,
            createVm = { savedStateHandle, _, component ->
                component.searchSubcomponentFactory.create().viewModelFactory.create(
                    savedStateHandle
                )
            },
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
        val navigationProvider = CompositionLocals.current<NavigationProvider>()
        return rememberScoped(rootEntry) {
            DaggerMovieSearchRootComponent.builder()
                .dataProvider(currentDataProvider)
                .platformProvider(currentPlatformProvider)
                .navigationProvider(navigationProvider)
                .build()
        }
    }
}