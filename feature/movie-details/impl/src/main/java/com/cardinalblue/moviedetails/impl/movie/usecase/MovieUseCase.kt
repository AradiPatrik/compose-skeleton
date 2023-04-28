package com.cardinalblue.moviedetails.impl.movie.usecase

import com.cardinalblue.platform.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

fun interface DoExample {
    suspend operator fun invoke(query: String?): String
}

class DoExampleUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : DoExample {
    override suspend fun invoke(query: String?) = withContext(ioDispatcher) {
        "example"
    }
}