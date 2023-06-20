package com.cardinalblue.moviedetails.impl.movie.di

import com.cardinalblue.moviedetails.impl.common.reviews.di.ReviewsModule
import com.cardinalblue.moviedetails.impl.movie.screen.MovieScreenViewModel
import com.cardinalblue.navigation.NavigationManagerModule
import com.cardinalblue.navigation.NavigationProvider
import com.cardinalblue.navigation.SubfeatureScoped
import dagger.Subcomponent

/**
 * The component for the screen inside the feature. It is providing the screen-wide dependencies.
 * If the sub-feature needs dependencies other than the ones provided in the root component, it
 * should be added inside the [MovieSubcomponent.Factory] as parameter.
 */
@SubfeatureScoped
@Subcomponent(
    modules = [
        MovieModule::class,
        ReviewsModule::class,
        NavigationManagerModule::class,
    ]
)
interface MovieSubcomponent : NavigationProvider {
    val viewModelFactory: MovieScreenViewModel.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(): MovieSubcomponent
    }
}