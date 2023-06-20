package com.cardinalblue.skeleton.di

import com.cardinalblue.navigation.NavigationManager
import com.cardinalblue.navigation.NavigationManagerImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface AppNavigationManagerModule {
    @Binds
    @Singleton
    fun bindNavigationManager(impl: NavigationManagerImpl): NavigationManager
}
