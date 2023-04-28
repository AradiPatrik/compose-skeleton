package com.cardinalblue.moviedetails.impl.movie.di

import com.cardinalblue.moviedetails.impl.movie.usecase.DoExample
import com.cardinalblue.moviedetails.impl.movie.usecase.DoExampleUseCase
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
    fun bindDoExampleUseCase(impl: DoExampleUseCase): DoExample
}