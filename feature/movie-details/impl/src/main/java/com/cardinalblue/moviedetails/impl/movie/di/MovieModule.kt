package com.cardinalblue.moviedetails.impl.movie.di

import com.cardinalblue.moviedetails.impl.movie.usecase.GetMovie
import com.cardinalblue.moviedetails.impl.movie.usecase.GetMovieUseCase
import com.cardinalblue.navigation.SubfeatureScoped
import dagger.Binds
import dagger.Module

/**
 * Add subfeature-wide available bindings here
 */
@Module
interface MovieModule {
    @Binds
    @SubfeatureScoped
    fun bindGetMovieUseCase(impl: GetMovieUseCase): GetMovie
}