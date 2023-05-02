package com.cardinalblue.moviedetails.impl.common.reviews.usecase

import androidx.paging.PagingData
import com.cardinalblue.domain.MovieRepository
import com.cardinalblue.domain.Review
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

fun interface GetReviews {
    fun invoke(movieId: Int): Flow<PagingData<Review>>
}

class GetReviewsUseCase @Inject constructor(
    private val repository: MovieRepository
) : GetReviews {

    override fun invoke(movieId: Int): Flow<PagingData<Review>> = repository.getReviews(movieId)
}