package com.cardinalblue.data.api

import com.cardinalblue.domain.MovieRepository

interface DataProvider {
    val moviesRepository: MovieRepository
}
