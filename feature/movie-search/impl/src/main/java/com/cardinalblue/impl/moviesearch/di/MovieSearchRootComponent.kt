package com.cardinalblue.impl.moviesearch.di

import androidx.navigation.NavController
import com.cardinalblue.data.api.DataProvider
import com.cardinalblue.moviesearch.api.MovieSearchProvider
import com.cardinalblue.impl.moviesearch.search.di.SearchSubcomponent
import com.cardinalblue.navigation.FeatureScoped
import com.cardinalblue.navigation.NavigationManagerModule
import com.cardinalblue.navigation.NavigationProvider
import com.cardinalblue.platform.PlatformProvider
import dagger.BindsInstance
import dagger.Component

/**
 * The root component for the feature. It is providing the feature-wide dependencies. Exposes them
 * to other features via [MovieSearchProvider].
 */
@FeatureScoped
@Component(
    dependencies = [
        DataProvider::class,
        PlatformProvider::class,
    ],
    modules = [
        MovieSearchRootModule::class,
        MovieSearchSubcomponentsModule::class,
    ]
)
interface MovieSearchRootComponent : MovieSearchProvider {
    val searchSubcomponentFactory: SearchSubcomponent.Factory

    @Component.Factory
    interface Factory {
        fun create(
            dataProvider: DataProvider,
            platformProvider: PlatformProvider,
            @BindsInstance navController: NavController,
        ): MovieSearchRootComponent
    }
}
