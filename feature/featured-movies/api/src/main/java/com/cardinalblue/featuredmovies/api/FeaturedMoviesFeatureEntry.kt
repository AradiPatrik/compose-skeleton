package com.cardinalblue.featuredmovies.api

import com.cardinalblue.navigation.EmptyInput
import com.cardinalblue.navigation.FeatureEntry
import com.cardinalblue.navigation.NavigationCommandProvider
import com.cardinalblue.navigation.createNavigationCommandProvider


/**
 * Define arguments and start destination for the feature.
 */
abstract class FeaturedMoviesFeatureEntry : FeatureEntry<EmptyInput> {
    companion object :
        NavigationCommandProvider<EmptyInput> by createNavigationCommandProvider("featured_movies")
}
