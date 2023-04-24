package com.cardinalblue.data.di

import com.cardinalblue.data.mapping.DataMapper
import com.cardinalblue.data.mapping.DataMapperImpl
import com.cardinalblue.data.repository.MovieRepositoryImpl
import com.cardinalblue.domain.MovieRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun bindMovieRepository(impl: MovieRepositoryImpl): MovieRepository
    
    @Binds
    @Singleton
    abstract fun bindMapper(impl: DataMapperImpl): DataMapper
}