package com.cardinalblue.data.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_remote_keys")
data class MovieRemoteKeyEntity(
    @PrimaryKey
    @ColumnInfo(name = "query") val query: String,
    @ColumnInfo(name = "next_key") val nextKey: Int?
)
