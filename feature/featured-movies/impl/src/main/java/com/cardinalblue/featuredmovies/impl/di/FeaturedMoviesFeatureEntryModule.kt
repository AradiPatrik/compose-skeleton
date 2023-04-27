package com.cardinalblue.featuredmovies.impl.di

import com.cardinalblue.featuredmovies.api.FeaturedMoviesFeatureEntry
import com.cardinalblue.featuredmovies.impl.entry.FeaturedMoviesFeatureEntryImpl
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
interface FeaturedMoviesFeatureEntryModule {
    @Binds
    @IntoMap
    @FeatureEntryKey(FeaturedMoviesFeatureEntry::class)
    @Singleton
    fun bindFeaturedMoviesEntry(impl: FeaturedMoviesFeatureEntryImpl): FeatureEntry<*>
}