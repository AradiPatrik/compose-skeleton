package com.cardinalblue.skeleton.di

import com.cardinalblue.impl.moviesearch.di.MovieSearchFeatureEntryModule
import com.cardinalblue.impl.profile.di.ProfileEntryFeatureModule
import dagger.Module

@Module(
    includes = [
        MovieSearchFeatureEntryModule::class,
        ProfileEntryFeatureModule::class,
    ]
)
class NavigationEntryModule