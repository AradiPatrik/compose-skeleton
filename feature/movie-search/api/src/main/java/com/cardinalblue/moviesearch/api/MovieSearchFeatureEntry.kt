package com.cardinalblue.moviesearch.api

import androidx.navigation.NamedNavArgument
import com.cardinalblue.navigation.EmptyInput
import com.cardinalblue.navigation.FeatureEntry
import com.cardinalblue.navigation.NavigationCommand

/**
 * Define arguments and start destination for the feature.
 */
abstract class MovieSearchFeatureEntry : FeatureEntry<EmptyInput> {
    final override val featureRoute: String = "movie-search"
    override val arguments = emptyList<NamedNavArgument>()

    final override fun destination(input: EmptyInput) = object : NavigationCommand {
        override val arguments: List<NamedNavArgument> = this@MovieSearchFeatureEntry.arguments
        override val destination: String = featureRoute
    }
}
