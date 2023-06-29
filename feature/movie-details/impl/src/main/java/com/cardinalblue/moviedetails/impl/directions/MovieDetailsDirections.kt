package com.cardinalblue.moviedetails.impl.directions

import com.cardinalblue.moviedetails.api.MovieDetailsFeatureEntry
import com.cardinalblue.moviedetails.api.MovieDetailsInput
import com.cardinalblue.navigation.NavDirection
import com.cardinalblue.navigation.createNavDirection

object MovieDetailsDirections {
    object Movie : NavDirection<MovieDetailsInput> by MovieDetailsFeatureEntry

    object Credits : NavDirection<MovieDetailsInput> by createNavDirection(
        "credits",
        inputType = MovieDetailsInput
    )
}