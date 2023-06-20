package com.cardinalblue.navigation

import androidx.navigation.NavController
import dagger.Module
import dagger.Provides

interface NavigationProvider {
    val navigationManager: NavigationManager
}

@Module
class NavigationManagerModule {
    @SubfeatureScoped
    @Provides
    fun provideNavigationManager(
        navController: NavController,
    ): NavigationManager = NavigationManagerImpl().apply {
        this.navController = navController
    }
}
