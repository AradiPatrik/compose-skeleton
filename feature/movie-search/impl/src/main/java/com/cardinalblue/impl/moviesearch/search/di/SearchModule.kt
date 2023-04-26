package com.cardinalblue.impl.moviesearch.search.di

import com.cardinalblue.impl.moviesearch.search.usecase.SearchMovies
import com.cardinalblue.impl.moviesearch.search.usecase.SearchMoviesUseCase
import com.cardinalblue.navigation.SubfeatureScoped
import dagger.Binds
import dagger.Module

/**
 * Add subfeature-wide available bindings here
 */
@Module
interface SearchModule {
    @Binds
    @SubfeatureScoped
    fun bindSearchMovies(impl: SearchMoviesUseCase): SearchMovies
}