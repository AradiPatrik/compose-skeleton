package com.cardinalblue.impl.entry

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.cardinalblue.api.DataProvider
import com.cardinalblue.api.MovieSearchFeatureEntry
import com.cardinalblue.impl.di.DaggerMovieSearchComponent
import com.cardinalblue.impl.di.MovieSearchComponent
import com.cardinalblue.impl.screen.search.SearchScreen
import com.cardinalblue.navigation.CompositionLocals
import com.cardinalblue.navigation.RootComponentHolder
import com.cardinalblue.navigation.injectedViewModel
import com.cardinalblue.navigation.rememberScoped
import com.cardinalblue.platform.PlatformProvider
import javax.inject.Inject

class MovieSearchFeatureEntryImpl @Inject constructor() : MovieSearchFeatureEntry(),
    RootComponentHolder<MovieSearchComponent> {
    override val rootRoute: String
        get() = "@movie-search"

    override fun NavGraphBuilder.navigation(
        navController: NavHostController,
    ) {
        navigation(startDestination = featureRoute, route = rootRoute) {
            composable(featureRoute, arguments) {
                val rootComponent = rootComponent(currentEntry = it, navController, it.arguments)

                val viewModel = injectedViewModel(it) {
                    rootComponent.viewModel
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
    ): MovieSearchComponent {
        val currentDataProvider = CompositionLocals.current<DataProvider>()
        val currentPlatformProvider = CompositionLocals.current<PlatformProvider>()
        return rememberScoped(rootEntry) {
            DaggerMovieSearchComponent.builder()
                .dataProvider(currentDataProvider)
                .platformProvider(currentPlatformProvider)
                .build()
        }

    }
}