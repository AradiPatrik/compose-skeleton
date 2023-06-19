package com.cardinalblue.moviedetails.api

import android.icu.util.Output
import com.cardinalblue.navigation.FeatureEntry
import com.cardinalblue.navigation.InputType
import com.cardinalblue.navigation.NavDirection
import com.cardinalblue.navigation.NavInput
import com.cardinalblue.navigation.NavOutput
import com.cardinalblue.navigation.OutputType
import com.cardinalblue.navigation.createInputType
import com.cardinalblue.navigation.createNavDirection
import com.cardinalblue.navigation.createOutputType
import com.squareup.moshi.JsonClass


/**
 * Define arguments and start destination for the feature.
 */
interface MovieDetailsFeatureEntry : FeatureEntry<MovieDetailsInput> {
    companion object : NavDirection<MovieDetailsInput> by createNavDirection(
        "movieDetails",
        inputType = MovieDetailsInput
    )
}

@JsonClass(generateAdapter = true)
data class MovieDetailsInput(val movieId: Int) : NavInput {
    companion object : InputType<MovieDetailsInput> by createInputType("movieId")
}

@JsonClass(generateAdapter = true)
data class MovieDetailsOutput(val mock: String) : NavOutput,
    OutputType<MovieDetailsOutput> by Companion {
    companion object : OutputType<MovieDetailsOutput> by createOutputType("mock")
}
