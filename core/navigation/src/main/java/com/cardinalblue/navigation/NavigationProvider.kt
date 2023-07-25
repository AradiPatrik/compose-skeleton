package com.cardinalblue.navigation

import androidx.navigation.NavController
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class AppCoroutineScope

interface AppCoroutineScopeProvider {
    @get:AppCoroutineScope
    val appCoroutineScope: CoroutineScope
}

interface NavigationProvider {
    val navigationManager: NavigationManager
}

@Module
class NavigationManagerModule {
    @SubfeatureScoped
    @Provides
    fun provideNavigationManager(
        navController: NavController,
    ): NavigationManager = NavigationManagerImpl(navController)
}
