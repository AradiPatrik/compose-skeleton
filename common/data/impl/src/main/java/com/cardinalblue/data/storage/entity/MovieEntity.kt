package com.cardinalblue.data.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo("local_id") val localId: String,
    @ColumnInfo("id") val id: Int,
    @ColumnInfo("ordinal") val ordinal: Int,
    @ColumnInfo("query") val query: String,
    @ColumnInfo("title") val title: String,
    @ColumnInfo("overview") val overview: String,
    @ColumnInfo(name = "backdrop_path") val backdropPath: String?,
    @ColumnInfo(name = "poster_path") val posterPath: String?,
    @ColumnInfo(name = "vote_average") val voteAverage: Float,
    @ColumnInfo(name = "vote_count") val voteCount: Int,
)