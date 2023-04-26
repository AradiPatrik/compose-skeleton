package com.cardinalblue.moviesearch.api

import androidx.navigation.NamedNavArgument
import com.cardinalblue.navigation.EmptyInput
import com.cardinalblue.navigation.FeatureEntry
import com.cardinalblue.navigation.NavigationCommand
import com.cardinalblue.navigation.NavigationCommandProvider

/**
 * Define arguments and start destination for the feature.
 */
abstract class MovieSearchFeatureEntry : FeatureEntry<EmptyInput> {
    companion object : NavigationCommandProvider<EmptyInput> {
        override val featureRoute: String = "movie-search"
        override val arguments = emptyList<NamedNavArgument>()

        override fun destination(input: EmptyInput) = object : NavigationCommand {
            override val arguments: List<NamedNavArgument> = this@Companion.arguments
            override val destination: String = featureRoute
        }
    }

}
