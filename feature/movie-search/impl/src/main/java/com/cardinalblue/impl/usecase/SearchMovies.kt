package com.cardinalblue.impl.usecase

import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import com.cardinalblue.domain.Movie
import com.cardinalblue.domain.MovieRepository
import com.cardinalblue.platform.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

fun interface SearchMovies {
    operator fun invoke(query: String?): Flow<PagingData<Movie>>
}

class SearchMoviesUseCase @Inject constructor(
    private val moviesRepository: MovieRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : SearchMovies {
    override fun invoke(query: String?): Flow<PagingData<Movie>> {
        val movies = if (query.isNullOrBlank()) getTrendingMovies() else searchMovies(query)
        return movies.flowOn(ioDispatcher)
    }

    private fun getTrendingMovies(): Flow<PagingData<Movie>> =
        moviesRepository.getTrendingMovies().map { movies ->
            val state = LoadState.NotLoading(endOfPaginationReached = true)
            val states = LoadStates(state, state, state)
            PagingData.from(movies, states)
        }.onStart {
            val state = LoadState.Loading
            val states = LoadStates(state, state, state)
            emit(PagingData.empty(states))
        }

    private fun searchMovies(query: String): Flow<PagingData<Movie>> =
        moviesRepository.getMovies(query)
}