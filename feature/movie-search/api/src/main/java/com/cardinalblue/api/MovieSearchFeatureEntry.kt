package com.cardinalblue.api

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink
import com.cardinalblue.navigation.AggregateFeatureEntry

abstract class MovieSearchFeatureEntry : AggregateFeatureEntry {
    final override val featureRoute: String = "movie-search"
    final override val arguments: List<NamedNavArgument> = emptyList()
    final override val deepLinks: List<NavDeepLink> = emptyList()

    fun destination() = featureRoute
}
