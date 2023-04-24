package com.cardinalblue.data.di

import com.cardinalblue.api.DataProvider
import com.cardinalblue.domain.MovieRepository
import com.cardinalblue.platform.PlatformProvider
import dagger.Component
import javax.inject.Singleton

@Component(
    dependencies = [PlatformProvider::class],
    modules = [DataModule::class, NetworkModule::class, StorageModule::class]
)
@Singleton
interface DataComponent : DataProvider {
    val movieRepository: MovieRepository
}