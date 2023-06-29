package com.cardinalblue.featuredmovies.impl.featuredmovieslist.di

import com.cardinalblue.featuredmovies.impl.featuredmovieslist.screen.FeaturedMoviesListScreenViewModel
import com.cardinalblue.navigation.NavigationManagerModule
import com.cardinalblue.navigation.NavigationProvider
import com.cardinalblue.navigation.SubfeatureSubcomponentFactory
import com.cardinalblue.navigation.SubfeatureScoped
import dagger.Subcomponent

/**
 * The component for the screen inside the feature. It is providing the screen-wide dependencies.
 * If the sub-feature needs dependencies other than the ones provided in the root component, it
 * should be added inside the [FeaturedMoviesListSubcomponent.Factory] as parameter.
 */
@SubfeatureScoped
@Subcomponent(
    modules = [
        FeaturedMoviesListModule::class,
        NavigationManagerModule::class,
    ]
)
interface FeaturedMoviesListSubcomponent : NavigationProvider {
    val viewModelFactory: FeaturedMoviesListScreenViewModel.Factory

    @Subcomponent.Factory
    interface Factory : SubfeatureSubcomponentFactory<FeaturedMoviesListSubcomponent>
}