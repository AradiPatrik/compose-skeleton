package com.cardinalblue.featuredmovies.impl.di

import androidx.navigation.NavController
import com.cardinalblue.data.api.DataProvider
import com.cardinalblue.featuredmovies.api.FeaturedMoviesProvider
import com.cardinalblue.featuredmovies.impl.featuredmovieslist.di.FeaturedMoviesListSubcomponent
import com.cardinalblue.navigation.FeatureScoped
import com.cardinalblue.navigation.NavigationManagerModule
import com.cardinalblue.navigation.NavigationProvider
import com.cardinalblue.platform.PlatformProvider
import dagger.BindsInstance
import dagger.Component

/**
 * The root component for the feature. It is providing the feature-wide dependencies. Exposes them
 * to other features via [FeaturedMoviesProvider].
 */
@FeatureScoped
@Component(
    dependencies = [
        DataProvider::class,
        PlatformProvider::class,
    ],
    modules = [
        FeaturedMoviesRootModule::class,
        FeaturedMoviesSubcomponentsModule::class,
    ]
)
interface FeaturedMoviesRootComponent : FeaturedMoviesProvider {
    val featuredMoviesListSubcomponentFactory: FeaturedMoviesListSubcomponent.Factory

    @Component.Factory
    interface Factory {
        fun create(
            dataProvider: DataProvider,
            platformProvider: PlatformProvider,
            @BindsInstance navController: NavController,
        ): FeaturedMoviesRootComponent
    }
}
