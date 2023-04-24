package com.cardinalblue.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import androidx.room.withTransaction
import com.cardinalblue.data.mapping.DataMapper
import com.cardinalblue.data.network.MovieDbApi
import com.cardinalblue.data.paging.MoviesRemoteMediatorFactory
import com.cardinalblue.data.paging.ReviewsRemoteMediatorFactory
import com.cardinalblue.data.storage.dao.MoviesDao.Companion.QUERY_TRENDING
import com.cardinalblue.data.storage.db.AppDatabase
import com.cardinalblue.domain.Credit
import com.cardinalblue.domain.Movie
import com.cardinalblue.domain.MovieRepository
import com.cardinalblue.domain.Review
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import java.net.UnknownHostException
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val moviesRemoteMediatorFactory: MoviesRemoteMediatorFactory,
    private val reviewsRemoteMediatorFactory: ReviewsRemoteMediatorFactory,
    private val database: AppDatabase,
    private val mapper: DataMapper,
    private val networkApi: MovieDbApi
) : MovieRepository {
    override fun getMovies(query: String): Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(
            pageSize = MovieRepository.PAGE_SIZE,
            enablePlaceholders = true,
        ),
        remoteMediator = moviesRemoteMediatorFactory.create(query),
        pagingSourceFactory = { database.moviesDao().getMovies(query) }
    ).flow.map { pagingData ->
        pagingData.map(mapper::storageToDomain)
    }

    override fun getTrendingMovies(): Flow<List<Movie>> = flow {
        val movies = getTrendingMoviesFromNetwork()
        if (movies != null) emit(movies)
        else emit(getTrendingMoviesFromStorage())
    }.onStart {
        val movies = getTrendingMoviesFromStorage()
        if (movies.isNotEmpty()) emit(movies)
    }.distinctUntilChanged()

    private suspend fun getTrendingMoviesFromNetwork(): List<Movie>? {
        val networkMovies = try {
            networkApi.getTrendingMovies(MovieDbApi.TIME_WINDOW_WEEK).results
        } catch (e: UnknownHostException) {
            return null
        }
        val domainMovies = networkMovies.map(mapper::networkToDomain)
        val storedMovies = networkMovies.mapIndexed { i, item ->
            mapper.networkToStorage(item, ordinal = i, query = QUERY_TRENDING)
        }
        database.withTransaction {
            database.moviesDao().deleteByQuery(QUERY_TRENDING)
            database.moviesDao().insertAll(storedMovies)
        }
        return domainMovies
    }

    private suspend fun getTrendingMoviesFromStorage(): List<Movie> =
        database.moviesDao()
            .getTrendingMovies()
            .map(mapper::storageToDomain)


    override suspend fun getMovieById(id: Int): Movie? {
        val movie = database.moviesDao().getMovieById(id)
        return movie?.let(mapper::storageToDomain)
    }

    override suspend fun getCredits(movieId: Int): List<Credit> {
        val networkCredits = networkApi.getCredits(movieId)
        return networkCredits.cast.map(mapper::networkToDomain)
    }

    override fun getReviews(movieId: Int): Flow<PagingData<Review>> = Pager(
        config = PagingConfig(
            pageSize = MovieRepository.PAGE_SIZE,
            enablePlaceholders = true,
        ),
        remoteMediator = reviewsRemoteMediatorFactory.create(movieId),
        pagingSourceFactory = { database.reviewsDao().getReviews(movieId) }
    ).flow.map { pagingData ->
        pagingData.map(mapper::storageToDomain)
    }
}