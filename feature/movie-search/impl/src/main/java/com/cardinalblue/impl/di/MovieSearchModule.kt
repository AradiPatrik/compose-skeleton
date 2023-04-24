package com.cardinalblue.impl.di

import com.cardinalblue.impl.usecase.SearchMovies
import com.cardinalblue.impl.usecase.SearchMoviesUseCase
import dagger.Binds
import dagger.Module

@Module
interface MovieSearchModule {
    @Binds
    fun bindSearchMovies(impl: SearchMoviesUseCase): SearchMovies
}