package com.cardinalblue.featuredmovies.api

import androidx.navigation.NamedNavArgument
import com.cardinalblue.navigation.EmptyInput
import com.cardinalblue.navigation.FeatureEntry
import com.cardinalblue.navigation.NavigationCommand
import com.cardinalblue.navigation.NavigationCommandProvider


/**
 * Define arguments and start destination for the feature.
 */
abstract class FeaturedMoviesFeatureEntry : FeatureEntry<EmptyInput> {
    companion object : NavigationCommandProvider<EmptyInput> {
        override val featureRoute: String = "featured-movies"
        override val arguments = emptyList<NamedNavArgument>()

        override fun destination(input: EmptyInput) = object : NavigationCommand {
            override val args: List<NamedNavArgument> = this@Companion.arguments
            override val destinationFeatureRoute: String = featureRoute
            override val destination: String = featureRoute
        }
    }
}
