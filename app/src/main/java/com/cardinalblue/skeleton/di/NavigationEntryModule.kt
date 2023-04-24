package com.cardinalblue.skeleton.di

import com.cardinalblue.impl.di.MovieSearchFeatureEntryModule
import dagger.Module

@Module(
    includes = [
        MovieSearchFeatureEntryModule::class,
    ]
)
class NavigationEntryModule