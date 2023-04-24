package com.cardinalblue.data.mapping

import com.cardinalblue.data.network.MovieDbApi
import com.cardinalblue.data.network.model.AuthorWire
import com.cardinalblue.data.network.model.MovieDbCredit
import com.cardinalblue.data.network.model.MovieWire
import com.cardinalblue.data.network.model.ReviewWire
import com.cardinalblue.data.storage.entity.AuthorEntity
import com.cardinalblue.data.storage.entity.MovieEntity
import com.cardinalblue.data.storage.entity.ReviewEntity
import com.cardinalblue.domain.Author
import com.cardinalblue.domain.Credit
import com.cardinalblue.domain.Movie
import com.cardinalblue.domain.Review
import java.math.RoundingMode
import javax.inject.Inject

class DataMapperImpl @Inject constructor() : DataMapper {
    override fun networkToStorage(movie: MovieWire, ordinal: Int, query: String) = with(movie) {
        MovieEntity(
            localId = query + "_" + id,
            id = id,
            ordinal = ordinal,
            query = query,
            title = title,
            overview = overview,
            backdropPath = backdropPath,
            posterPath = posterPath,
            voteAverage = voteAverage.rounded(),
            voteCount = voteCount
        )
    }

    override fun networkToStorage(review: ReviewWire, movieId: Int) = with(review) {
        ReviewEntity(
            id = id,
            movieId = movieId,
            author = networkToStorage(authorDetails),
            content = content,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }

    private fun networkToStorage(author: AuthorWire) = with(author) {
        AuthorEntity(
            name = name,
            username = username,
            avatarPath = avatarPath,
            rating = rating
        )
    }

    override fun networkToDomain(movie: MovieWire) = with(movie) {
        Movie(
            id = id,
            title = title,
            overview = overview,
            thumbPosterUrl = buildPosterUrl(posterPath, isThumb = true),
            posterUrl = buildPosterUrl(posterPath),
            thumbBackdropUrl = buildBackdropUrl(backdropPath, isThumb = true),
            backdropUrl = buildBackdropUrl(backdropPath),
            voteAverage = voteAverage.rounded(),
            voteCount = voteCount
        )
    }

    override fun networkToDomain(credit: MovieDbCredit) = with(credit) {
        Credit(
            id = id,
            name = name,
            character = character,
            department = knownForDepartment,
            profilePath = profilePath,
            order = order
        )
    }

    override fun storageToDomain(movie: MovieEntity) = with(movie) {
        Movie(
            id = id,
            title = title,
            overview = overview,
            thumbPosterUrl = buildPosterUrl(posterPath, isThumb = true),
            posterUrl = buildPosterUrl(posterPath),
            thumbBackdropUrl = buildBackdropUrl(backdropPath, isThumb = true),
            backdropUrl = buildBackdropUrl(backdropPath),
            voteAverage = voteAverage,
            voteCount = voteCount
        )
    }

    private fun Float.rounded(): Float =
        toBigDecimal().setScale(1, RoundingMode.UP).toFloat()

    override fun storageToDomain(review: ReviewEntity) = with(review) {
        Review(
            id = id,
            author = storageToDomain(author),
            content = content,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }

    private fun storageToDomain(author: AuthorEntity) = with(author) {
        Author(
            name = name,
            username = username,
            avatarPath = buildProfileUrl(avatarPath),
            rating = rating
        )
    }

    private fun buildPosterUrl(path: String?, isThumb: Boolean = false): String? {
        if (path == null) return null
        val resolution = if (isThumb) "w92" else "w500"
        return "${MovieDbApi.IMAGES_BASE_URL}$resolution${path}"
    }

    private fun buildBackdropUrl(path: String?, isThumb: Boolean = false): String? {
        if (path == null) return null
        val resolution = if (isThumb) "w300" else "original"
        return "${MovieDbApi.IMAGES_BASE_URL}$resolution${path}"
    }

    private fun buildProfileUrl(path: String?): String? {
        if (path == null) return null
        if (path.startsWith("http")) return path
        if (path.startsWith("/http")) return path.substring(1)
        val resolution = "w185"
        return "${MovieDbApi.IMAGES_BASE_URL}${resolution}${path}"
    }
}