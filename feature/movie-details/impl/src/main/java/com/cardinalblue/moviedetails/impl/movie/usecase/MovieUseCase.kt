package com.cardinalblue.moviedetails.impl.movie.usecase

import com.cardinalblue.domain.Movie
import com.cardinalblue.domain.MovieRepository
import com.cardinalblue.platform.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

fun interface GetMovie {
    suspend operator fun invoke(movieId: Int): Movie
}

class GetMovieUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val repository: MovieRepository,
) : GetMovie {
    override suspend fun invoke(movieId: Int): Movie = withContext(ioDispatcher) {
        repository.getMovieById(movieId) ?: error("Movie not found")
    }
}