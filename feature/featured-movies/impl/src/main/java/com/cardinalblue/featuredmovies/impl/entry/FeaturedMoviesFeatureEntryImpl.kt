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
class FeaturedMoviesFeatureEntryImpl @Inject constructor() : BaseFeatureEntry<EmptyInput, FeaturedMoviesRootComponent>(
    rootRoute = "@featured-movies",
    FeaturedMoviesFeatureEntry.Direction.featureRoute
), FeaturedMoviesFeatureEntry {
    override fun NavGraphBuilder.buildNavigation(
        navController: NavHostController,
        navigate: NavHostController.(NavigationCommand) -> Unit
    ) {
        addNode(
            direction = FeaturedMoviesFeatureEntry.Direction,
            navController = navController,
            navigate = navigate,
            createVm = { _, _, component -> component.featuredMoviesListSubcomponentFactory.create().viewModel },
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
        val navigationProvider = CompositionLocals.current<NavigationProvider>()
        return rememberScoped(rootEntry) {
            DaggerFeaturedMoviesRootComponent.builder()
                .dataProvider(currentDataProvider)
                .platformProvider(currentPlatformProvider)
                .navigationProvider(navigationProvider)
                .build()
        }
    }
}