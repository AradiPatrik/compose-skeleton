package com.cardinalblue.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class MovieDbReviewsResponse(
    val id: String,
    val page: Int,
    val results: List<ReviewWire>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)

@JsonClass(generateAdapter = true)
data class ReviewWire(
    val id: String,
    val author: String,
    @Json(name = "author_details")
    val authorDetails: AuthorWire,
    val content: String,
    @Json(name = "created_at")
    val createdAt: Date,
    @Json(name = "updated_at")
    val updatedAt: Date
)

@JsonClass(generateAdapter = true)
data class AuthorWire(
    val name: String,
    val username: String,
    @Json(name = "avatar_path")
    val avatarPath: String?,
    val rating: Float?
)
