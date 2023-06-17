package com.cardinalblue.moviesearch.api

import androidx.navigation.NamedNavArgument
import com.cardinalblue.navigation.EmptyInput
import com.cardinalblue.navigation.FeatureEntry
import com.cardinalblue.navigation.NavigationCommand
import com.cardinalblue.navigation.NavigationCommandProvider
import com.cardinalblue.navigation.createNavigationCommandProvider

/**
 * Define arguments and start destination for the feature.
 */
abstract class MovieSearchFeatureEntry : FeatureEntry<EmptyInput> {
    companion object : NavigationCommandProvider<EmptyInput> by createNavigationCommandProvider("movie_search")
}
