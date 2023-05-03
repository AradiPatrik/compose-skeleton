package com.cardinalblue.domain

data class Author(
    val name: String,
    val username: String,
    val avatarPath: String?,
    val rating: Float?
)