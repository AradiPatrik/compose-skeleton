package com.cardinalblue.moviedetails.api

import android.os.Bundle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.cardinalblue.navigation.FeatureEntry
import com.cardinalblue.navigation.InputType
import com.cardinalblue.navigation.NavInput
import com.cardinalblue.navigation.NavigationCommand
import com.cardinalblue.navigation.NavigationCommandProvider
import com.cardinalblue.navigation.createInputType
import com.cardinalblue.navigation.createNavigationCommandProvider
import com.squareup.moshi.JsonClass


/**
 * Define arguments and start destination for the feature.
 */
abstract class MovieDetailsFeatureEntry : FeatureEntry<MovieDetailsInput> {
    companion object :
        NavigationCommandProvider<MovieDetailsInput> by createNavigationCommandProvider(
            "movieDetails",
            inputType = MovieDetailsInput
        )
}

@JsonClass(generateAdapter = true)
data class MovieDetailsInput(val movieId: Int) : NavInput {
    companion object : InputType<MovieDetailsInput> by createInputType("movieId")
}
