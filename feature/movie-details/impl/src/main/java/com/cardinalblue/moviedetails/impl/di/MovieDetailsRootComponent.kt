package com.cardinalblue.moviedetails.impl.di

import com.cardinalblue.data.api.DataProvider
import com.cardinalblue.moviedetails.api.MovieDetailsProvider
import com.cardinalblue.moviedetails.impl.credits.di.CreditsSubcomponent
import com.cardinalblue.moviedetails.impl.movie.di.MovieSubcomponent
import com.cardinalblue.navigation.FeatureScoped
import com.cardinalblue.navigation.NavigationProvider
import com.cardinalblue.platform.PlatformProvider
import dagger.Component

/**
 * The root component for the feature. It is providing the feature-wide dependencies. Exposes them
 * to other features via [MovieDetailsProvider].
 */
@FeatureScoped
@Component(
    dependencies = [
        DataProvider::class,
        PlatformProvider::class,
        NavigationProvider::class,
    ],
    modules = [
        MovieDetailsRootModule::class,
        MovieDetailsSubcomponentsModule::class
    ]
)
interface MovieDetailsRootComponent : MovieDetailsProvider {
    val movieSubcomponentFactory: MovieSubcomponent.Factory
    val creditsSubcomponentFactory: CreditsSubcomponent.Factory

    @Component.Factory
    interface Factory {
        fun create(
            dataProvider: DataProvider,
            platformProvider: PlatformProvider,
            navigationProvider: NavigationProvider,
        ): MovieDetailsRootComponent
    }
}
