package com.cardinalblue.data.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "review_remote_keys")
data class ReviewRemoteKeysEntity(
    @PrimaryKey
    @ColumnInfo(name = "movie_id") val movieId: Int,
    @ColumnInfo(name = "next_key") val nextKey: Int?
)