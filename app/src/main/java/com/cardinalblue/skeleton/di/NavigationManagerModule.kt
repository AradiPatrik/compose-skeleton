package com.cardinalblue.skeleton.di

import com.cardinalblue.navigation.NavigationManager
import dagger.Module

@Module
class NavigationManagerModule {
    fun provideNavigationManager(): NavigationManager = NavigationManager()
}
