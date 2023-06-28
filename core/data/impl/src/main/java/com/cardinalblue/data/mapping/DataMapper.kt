package com.cardinalblue.data.mapping

import com.cardinalblue.data.network.model.MovieDbCredit
import com.cardinalblue.data.network.model.MovieWire
import com.cardinalblue.data.network.model.ReviewWire
import com.cardinalblue.data.storage.entity.MovieEntity
import com.cardinalblue.data.storage.entity.ReviewEntity
import com.cardinalblue.domain.Credit
import com.cardinalblue.domain.Movie
import com.cardinalblue.domain.Review

interface DataMapper {
    fun networkToStorage(movie: MovieWire, ordinal: Int, query: String): MovieEntity

    fun networkToStorage(review: ReviewWire, movieId: Int): ReviewEntity

    fun networkToDomain(movie: MovieWire): Movie

    fun networkToDomain(credit: MovieDbCredit): Credit

    fun storageToDomain(movie: MovieEntity): Movie

    fun storageToDomain(review: ReviewEntity): Review
}