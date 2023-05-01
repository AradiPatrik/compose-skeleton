package com.cardinalblue.moviedetails.impl.credits.usecase

import com.cardinalblue.domain.Credit
import com.cardinalblue.domain.MovieRepository
import com.cardinalblue.navigation.SubfeatureScoped
import com.cardinalblue.platform.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

fun interface GetCredits {
    suspend operator fun invoke(movieId: Int): List<Credit>
}

class GetCreditsUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : GetCredits {
    override suspend fun invoke(movieId: Int): List<Credit> = withContext(ioDispatcher) {
        movieRepository.getCredits(movieId)
    }
}
