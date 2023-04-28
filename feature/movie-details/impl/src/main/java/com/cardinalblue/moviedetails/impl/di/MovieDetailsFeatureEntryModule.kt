package com.cardinalblue.moviedetails.impl.di

import com.cardinalblue.moviedetails.api.MovieDetailsFeatureEntry
import com.cardinalblue.moviedetails.impl.entry.MovieDetailsFeatureEntryImpl
import com.cardinalblue.navigation.FeatureEntry
import com.cardinalblue.navigation.FeatureEntryKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

/**
 * Binding the navigation entry points into this feature
 */
@Module
interface MovieDetailsFeatureEntryModule {
    @Binds
    @IntoMap
    @FeatureEntryKey(MovieDetailsFeatureEntry::class)
    @Singleton
    fun bindMovieDetailsEntry(impl: MovieDetailsFeatureEntryImpl): FeatureEntry<*>
}