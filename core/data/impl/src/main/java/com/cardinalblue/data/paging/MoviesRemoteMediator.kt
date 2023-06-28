package com.cardinalblue.data.paging

import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.cardinalblue.data.mapping.DataMapper
import com.cardinalblue.data.network.MovieDbApi
import com.cardinalblue.data.network.MovieDbApi.Companion.PAGE_SIZE
import com.cardinalblue.data.storage.db.AppDatabase
import com.cardinalblue.data.storage.entity.MovieEntity
import com.cardinalblue.data.storage.entity.MovieRemoteKeyEntity
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import retrofit2.HttpException
import java.io.IOException

class MoviesRemoteMediator @AssistedInject constructor(
    @Assisted private val query: String,
    private val networkService: MovieDbApi,
    private val database: AppDatabase,
    private val mapper: DataMapper,
) : RemoteMediator<Int, MovieEntity>() {
    private val moviesDao = database.moviesDao()
    private val movieRemoteKeysDao = database.movieRemoteKeysDao()

    override suspend fun initialize(): InitializeAction {

        return super.initialize()
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {
        return try {
            tryLoad(loadType)
        } catch (exception: IOException) {
            MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            MediatorResult.Error(exception)
        }
    }

    private suspend fun tryLoad(
        loadType: LoadType,
    ): MediatorResult {
        val loadKey = when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> null // can never happen. End paging if it does
            LoadType.APPEND -> movieRemoteKeysDao.getMovieRemoteKeyByQuery(query)?.nextKey
        } ?: return MediatorResult.Success(endOfPaginationReached = true)

        val searchResult = networkService.searchMovies(query, page = loadKey)

        val movies = searchResult.results
            .filter { it.posterPath != null }
            .mapIndexed { i, item ->
                mapper.networkToStorage(item, ordinal = (searchResult.page - 1) * PAGE_SIZE + i, query)
            }

        val endOfPaginationReached = movies.isEmpty()

        writeToDatabase(movies, MovieRemoteKeyEntity(query, searchResult.page + 1))

        return MediatorResult.Success(endOfPaginationReached)
    }

    private suspend fun writeToDatabase(
        movies: List<MovieEntity>,
        remoteKeys: MovieRemoteKeyEntity
    ) {
        database.withTransaction {
            moviesDao.insertAll(movies)
            movieRemoteKeysDao.insertOrReplace(remoteKeys)
        }
    }
}