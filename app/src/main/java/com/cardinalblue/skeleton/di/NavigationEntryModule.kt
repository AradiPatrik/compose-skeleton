package com.cardinalblue.skeleton.di

import com.cardinalblue.impl.moviesearch.di.MovieSearchFeatureEntryModule
import com.cardinalblue.featuredmovies.impl.di.FeaturedMoviesFeatureEntryModule
import com.cardinalblue.profile.impl.di.ProfileFeatureEntryModule
import dagger.Module

@Module(
    includes = [
        ProfileFeatureEntryModule::class,
        FeaturedMoviesFeatureEntryModule::class,
        MovieSearchFeatureEntryModule::class,
    ]
)
class NavigationEntryModule