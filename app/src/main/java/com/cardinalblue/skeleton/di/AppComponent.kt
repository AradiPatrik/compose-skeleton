package com.cardinalblue.skeleton.di

import com.cardinalblue.data.api.DataProvider
import com.cardinalblue.platform.PlatformProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [
        PlatformProvider::class,
        DataProvider::class,
    ],
    modules = [
        NavigationEntryModule::class,
        AppNavigationManagerModule::class,
    ]
)
interface AppComponent : AppProvider