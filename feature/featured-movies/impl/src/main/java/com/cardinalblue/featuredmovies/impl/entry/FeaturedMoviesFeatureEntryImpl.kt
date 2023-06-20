package com.cardinalblue.featuredmovies.impl.entry

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.cardinalblue.data.api.DataProvider
import com.cardinalblue.featuredmovies.api.FeaturedMoviesFeatureEntry
import com.cardinalblue.featuredmovies.impl.di.DaggerFeaturedMoviesRootComponent
import com.cardinalblue.featuredmovies.impl.di.FeaturedMoviesRootComponent
import com.cardinalblue.featuredmovies.impl.featuredmovieslist.screen.FeaturedMoviesListScreen
import com.cardinalblue.navigation.BaseFeatureEntry
import com.cardinalblue.navigation.CompositionLocals
import com.cardinalblue.navigation.EmptyInput
import com.cardinalblue.navigation.ToDestinationCommand
import com.cardinalblue.navigation.rememberScoped
import com.cardinalblue.platform.PlatformProvider
import javax.inject.Inject

/**
 * The entry point for the feature. Provides root component and navigation graph for the feature.
 */
class FeaturedMoviesFeatureEntryImpl @Inject constructor() : BaseFeatureEntry<EmptyInput, FeaturedMoviesRootComponent>(
    rootRoute = "@featured-movies",
    FeaturedMoviesFeatureEntry.Direction.featureRoute
), FeaturedMoviesFeatureEntry {
    override fun NavGraphBuilder.buildNavigation(
        navController: NavHostController,
        navigate: NavHostController.(ToDestinationCommand) -> Unit
    ) {
        addNode(
            direction = FeaturedMoviesFeatureEntry.Direction,
            navController = navController,
            navigate = navigate,
            createSubcomponent = { it.featuredMoviesListSubcomponentFactory.create() },
            createVm = { _, _, component -> component.viewModel },
            content = { FeaturedMoviesListScreen(it) }
        )
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
            DaggerFeaturedMoviesRootComponent.factory().create(
                dataProvider = currentDataProvider,
                platformProvider = currentPlatformProvider,
                navController = navController,
            )
        }
    }
}