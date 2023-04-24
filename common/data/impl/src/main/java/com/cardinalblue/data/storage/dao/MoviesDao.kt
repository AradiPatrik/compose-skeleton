package com.cardinalblue.data.storage.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.cardinalblue.data.storage.entity.MovieEntity

@Dao
interface MoviesDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertAll(movies: List<MovieEntity>)

    @Query("SELECT * FROM movies WHERE `query` = :query ORDER BY ordinal ASC")
    fun getMovies(query: String): PagingSource<Int, MovieEntity>

    @Query("SELECT * FROM movies WHERE `query` = '$QUERY_TRENDING' ORDER BY ordinal ASC")
    suspend fun getTrendingMovies(): List<MovieEntity>

    @Query("SELECT * FROM movies WHERE id = :id")
    suspend fun getMovieById(id: Int): MovieEntity?

    @Query("DELETE FROM movies WHERE `query` = :query")
    suspend fun deleteByQuery(query: String)

    @Query("DELETE FROM movies")
    suspend fun deleteAll()

    companion object {
        const val QUERY_TRENDING = "@trending"
    }
}