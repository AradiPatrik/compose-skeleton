package com.cardinalblue.featuredmovies.impl.entry

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.cardinalblue.data.api.DataProvider
import com.cardinalblue.featuredmovies.api.FeaturedMoviesFeatureEntry
import com.cardinalblue.featuredmovies.impl.di.DaggerFeaturedMoviesRootComponent
import com.cardinalblue.featuredmovies.impl.di.FeaturedMoviesRootComponent
import com.cardinalblue.featuredmovies.impl.featuredmovieslist.di.FeaturedMoviesListSubcomponent
import com.cardinalblue.featuredmovies.impl.featuredmovieslist.screen.FeaturedMoviesListScreen
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
class FeaturedMoviesFeatureEntryImpl @Inject constructor() : BaseFeatureEntry<EmptyInput, FeaturedMoviesRootComponent>(
    rootRoute = "@featured-movies",
    FeaturedMoviesFeatureEntry.Direction.featureRoute
), FeaturedMoviesFeatureEntry {
    override fun FeatureGraphBuilderScope.buildNavigation() {
        addNode(
            direction = FeaturedMoviesFeatureEntry.Direction,
            subcomponentFactory = FeaturedMoviesRootComponent::featuredMoviesListSubcomponentFactory,
            viewModelFactory = FeaturedMoviesListSubcomponent::viewModelFactory,
            content = { FeaturedMoviesListScreen(viewModel = it) }
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