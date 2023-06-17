package com.cardinalblue.moviedetails.impl.directions

import com.cardinalblue.moviedetails.api.MovieDetailsFeatureEntry
import com.cardinalblue.moviedetails.api.MovieDetailsInput
import com.cardinalblue.navigation.NavigationCommandProvider
import com.cardinalblue.navigation.createNavigationCommandProvider

object MovieDetailsDirections {
    object Movie : NavigationCommandProvider<MovieDetailsInput> by MovieDetailsFeatureEntry

    object Credits :
        NavigationCommandProvider<MovieDetailsInput> by createNavigationCommandProvider(
            "credits",
            inputType = MovieDetailsInput
        )
}