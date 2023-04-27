package com.cardinalblue.skeleton.di

import com.cardinalblue.impl.moviesearch.di.MovieSearchFeatureEntryModule
import com.cardinalblue.impl.profile.di.ProfileEntryFeatureModule
import com.cardinalblue.featuredmovies.impl.di.FeaturedMoviesFeatureEntryModule
import dagger.Module

@Module(
    includes = [
        FeaturedMoviesFeatureEntryModule::class,
        MovieSearchFeatureEntryModule::class,
        ProfileEntryFeatureModule::class,
    ]
)
class NavigationEntryModule