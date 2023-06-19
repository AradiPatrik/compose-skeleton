package com.cardinalblue.moviesearch.api

import com.cardinalblue.navigation.EmptyInput
import com.cardinalblue.navigation.FeatureEntry
import com.cardinalblue.navigation.NavDirection
import com.cardinalblue.navigation.NavigationCommandProvider
import com.cardinalblue.navigation.createNavDirection

/**
 * Define arguments and start destination for the feature.
 */
interface MovieSearchFeatureEntry : FeatureEntry<EmptyInput> {
    companion object Direction : NavDirection<EmptyInput> by createNavDirection("movie_search")
}
