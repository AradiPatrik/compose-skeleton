package com.cardinalblue.skeleton.di

import com.cardinalblue.api.DataProvider
import com.cardinalblue.platform.PlatformProvider
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [
        PlatformProvider::class,
        DataProvider::class,
    ],
    modules = [
        NavigationEntryModule::class,
    ]
)
interface AppComponent : AppProvider