package com.cardinalblue.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.cardinalblue.data.mapping.DataMapper
import com.cardinalblue.data.network.MovieDbApi
import com.cardinalblue.data.storage.db.AppDatabase
import com.cardinalblue.data.storage.entity.ReviewEntity
import com.cardinalblue.data.storage.entity.ReviewRemoteKeysEntity
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import retrofit2.HttpException
import java.io.IOException

class ReviewsRemoteMediator @AssistedInject constructor(
    @Assisted private val movieId: Int,
    private val database: AppDatabase,
    private val networkService: MovieDbApi,
    private val mapper: DataMapper,
) : RemoteMediator<Int, ReviewEntity>() {
    private val reviewsDao = database.reviewsDao()
    private val reviewRemoteKeysDao = database.reviewRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ReviewEntity>
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
        loadType: LoadType
    ): MediatorResult {
        val loadKey = when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> null // can never happen. End paging if it does
            LoadType.APPEND -> reviewRemoteKeysDao.getReviewRemoteKeysById(movieId)?.nextKey
        } ?: return MediatorResult.Success(endOfPaginationReached = true)

        val searchResult = networkService.getReviews(movieId, page = loadKey)

        val reviews = searchResult.results.map { mapper.networkToStorage(it, movieId) }
        val nextKey = ReviewRemoteKeysEntity(movieId, loadKey + 1)
        writeToDb(loadType, reviews, nextKey)

        return MediatorResult.Success(endOfPaginationReached = reviews.isEmpty())
    }

    private suspend fun writeToDb(
        loadType: LoadType,
        reviews: List<ReviewEntity>,
        nextKey: ReviewRemoteKeysEntity,
    ) {
        database.withTransaction {
            if (loadType == LoadType.REFRESH) {
                reviewsDao.deleteAll()
            }

            reviewRemoteKeysDao.insertOrReplace(nextKey)
            reviewsDao.insertAll(reviews)
        }
    }
}