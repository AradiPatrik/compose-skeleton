package com.cardinalblue.data.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "reviews")
data class ReviewEntity(
    @PrimaryKey
    @ColumnInfo("id") val id: String,
    @ColumnInfo("movie_id") val movieId: Int,
    @Embedded val author: AuthorEntity,
    @ColumnInfo("content") val content: String,
    @ColumnInfo("created_at") val createdAt: Date,
    @ColumnInfo("updated_at") val updatedAt: Date,
)

data class AuthorEntity(
    @ColumnInfo("name") val name: String,
    @ColumnInfo("username") val username: String,
    @ColumnInfo("avatar_path") val avatarPath: String?,
    @ColumnInfo("rating") val rating: Int?,
)