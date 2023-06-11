package com.cardinalblue.moviedetails.impl.directions

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.cardinalblue.moviedetails.api.MovieDetailsFeatureEntry
import com.cardinalblue.moviedetails.api.MovieDetailsFeatureEntry.Companion.ARG_MOVIE_ID
import com.cardinalblue.moviedetails.api.MovieDetailsInput
import com.cardinalblue.navigation.NavigationCommand
import com.cardinalblue.navigation.NavigationCommandProvider

object MovieDetailsDirections {
    object Movie : NavigationCommandProvider<MovieDetailsInput> by MovieDetailsFeatureEntry

    object Credits : NavigationCommandProvider<MovieDetailsInput> {
        override val featureRoute: String = "credits/{$ARG_MOVIE_ID}"
        override val arguments = listOf(
            navArgument(ARG_MOVIE_ID) {
                type = NavType.IntType
            }
        )

        override fun destination(input: MovieDetailsInput) = object : NavigationCommand {
            override val args = Credits.arguments
            override val destinationFeatureRoute = MovieDetailsFeatureEntry.featureRoute
            override val destination: String = "credits/${input.movieId}"
        }
    }
}