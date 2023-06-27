package com.cardinalblue.skeleton.di

import androidx.lifecycle.viewModelScope
import com.cardinalblue.navigation.AppCoroutineScope
import com.cardinalblue.navigation.CompositionScoped
import com.cardinalblue.navigation.NavigationManager
import com.cardinalblue.navigation.NavigationManagerImpl
import com.cardinalblue.skeleton.AppViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
interface NavigationModule {
    @Binds
    @CompositionScoped
    fun bindNavigationManager(impl: NavigationManagerImpl): NavigationManager
}

@Module
class NavigationProvidersModule {
    @Provides
    @CompositionScoped
    @AppCoroutineScope
    fun provideAppCoroutineScope(appViewModel: AppViewModel): CoroutineScope {
        return appViewModel.viewModelScope
    }
}
