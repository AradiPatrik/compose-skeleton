package com.cardinalblue.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDbMoviesResponse(
    val page: Int,
    val results: List<MovieWire>
)

@JsonClass(generateAdapter = true)
data class MovieWire(
    val id: Int,
    val title: String,
    val overview: String,
    @Json(name = "backdrop_path")
    val backdropPath: String?,
    @Json(name = "poster_path")
    val posterPath: String?,
    @Json(name = "vote_average")
    val voteAverage: Float,
    @Json(name = "vote_count")
    val voteCount: Int
)