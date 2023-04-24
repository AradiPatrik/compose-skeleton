package com.cardinalblue.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.cardinalblue.data.storage.entity.ReviewRemoteKeysEntity

@Dao
interface ReviewRemoteKeysDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertOrReplace(remoteKeys: ReviewRemoteKeysEntity)

    @Query("SELECT * FROM review_remote_keys WHERE movie_id = :movieId")
    suspend fun getReviewRemoteKeysById(movieId: Int): ReviewRemoteKeysEntity?

    @Query("DELETE FROM review_remote_keys")
    suspend fun deleteAll()
}