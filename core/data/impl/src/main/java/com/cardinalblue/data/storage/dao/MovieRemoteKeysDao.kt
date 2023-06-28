package com.cardinalblue.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.cardinalblue.data.storage.entity.MovieRemoteKeyEntity

@Dao
interface MovieRemoteKeysDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertOrReplace(remoteKeys: MovieRemoteKeyEntity)

    @Query("SELECT * FROM movie_remote_keys WHERE `query` = :query")
    suspend fun getMovieRemoteKeyByQuery(query: String): MovieRemoteKeyEntity?

    @Query("DELETE FROM movie_remote_keys")
    suspend fun deleteAll()
}