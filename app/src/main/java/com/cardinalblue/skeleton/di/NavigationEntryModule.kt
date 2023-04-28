package com.cardinalblue.skeleton.di

import com.cardinalblue.impl.moviesearch.di.MovieSearchFeatureEntryModule
import com.cardinalblue.featuredmovies.impl.di.FeaturedMoviesFeatureEntryModule
import com.cardinalblue.profile.impl.di.ProfileFeatureEntryModule
import com.cardinalblue.moviedetails.impl.di.MovieDetailsFeatureEntryModule
import dagger.Module

@Module(
    includes = [
        MovieDetailsFeatureEntryModule::class,
        ProfileFeatureEntryModule::class,
        FeaturedMoviesFeatureEntryModule::class,
        MovieSearchFeatureEntryModule::class,
    ]
)
class NavigationEntryModule