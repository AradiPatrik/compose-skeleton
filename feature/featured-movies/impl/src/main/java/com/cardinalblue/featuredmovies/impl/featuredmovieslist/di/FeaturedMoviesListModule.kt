package com.cardinalblue.featuredmovies.impl.featuredmovieslist.di

import com.cardinalblue.featuredmovies.impl.featuredmovieslist.usecase.DoExample
import com.cardinalblue.featuredmovies.impl.featuredmovieslist.usecase.DoExampleUseCase
import com.cardinalblue.navigation.SubfeatureScoped
import dagger.Binds
import dagger.Module

/**
 * Add subfeature-wide available bindings here
 */
@Module
interface FeaturedMoviesListModule {
    @Binds
    @SubfeatureScoped
    fun bindDoExampleUseCase(impl: DoExampleUseCase): DoExample
}