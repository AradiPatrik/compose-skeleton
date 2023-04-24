package com.cardinalblue.api

import com.cardinalblue.domain.MovieRepository

interface DataProvider {
    val moviesRepository: MovieRepository
}
