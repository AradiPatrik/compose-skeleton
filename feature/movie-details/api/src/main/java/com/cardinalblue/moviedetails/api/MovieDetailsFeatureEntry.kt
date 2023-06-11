package com.cardinalblue.moviedetails.api

import android.os.Bundle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.cardinalblue.navigation.FeatureEntry
import com.cardinalblue.navigation.NavInput
import com.cardinalblue.navigation.NavigationCommand
import com.cardinalblue.navigation.NavigationCommandProvider


/**
 * Define arguments and start destination for the feature.
 */
abstract class MovieDetailsFeatureEntry : FeatureEntry<MovieDetailsInput> {
    companion object : NavigationCommandProvider<MovieDetailsInput> {
        const val ARG_MOVIE_ID = "ARG_MOVIE_ID"

        override val featureRoute: String = "movie-details/{$ARG_MOVIE_ID}"
        override val arguments = listOf(
            navArgument(ARG_MOVIE_ID) {
                type = NavType.IntType
            }
        )

        override fun destination(input: MovieDetailsInput) = object : NavigationCommand {
            override val args: List<NamedNavArgument> = this@Companion.arguments
            override val destinationFeatureRoute: String = featureRoute
            override val destination: String = "movie-details/${input.movieId}"
        }
    }
}

data class MovieDetailsInput(val movieId: Int) : NavInput {
    companion object {
        fun fromBundle(bundle: Bundle?): MovieDetailsInput {
            bundle ?: error("MovieDetailsInput bundle is null")
            return MovieDetailsInput(
                movieId = bundle.getInt(MovieDetailsFeatureEntry.ARG_MOVIE_ID)
            )
        }
    }
}
