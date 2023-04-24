package com.cardinalblue.impl.di

import com.cardinalblue.api.MovieSearchFeatureEntry
import com.cardinalblue.impl.entry.MovieSearchFeatureEntryImpl
import com.cardinalblue.navigation.FeatureEntry
import com.cardinalblue.navigation.FeatureEntryKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
interface MovieSearchFeatureEntryModule {
    @Binds
    @IntoMap
    @FeatureEntryKey(MovieSearchFeatureEntry::class)
    @Singleton
    fun bindMovieSearchEntry(impl: MovieSearchFeatureEntryImpl): FeatureEntry
}