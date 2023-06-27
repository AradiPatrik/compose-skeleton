package com.cardinalblue.skeleton.di

import androidx.navigation.NavController
import com.cardinalblue.navigation.AppCoroutineScopeProvider
import com.cardinalblue.navigation.CompositionScoped
import com.cardinalblue.navigation.FeatureEntriesProvider
import com.cardinalblue.navigation.NavigationProvider
import com.cardinalblue.skeleton.AppViewModel
import dagger.BindsInstance
import dagger.Module
import dagger.Subcomponent

@Subcomponent(
    modules = [
        NavigationModule::class,
        NavigationProvidersModule::class
    ]
)
@CompositionScoped
interface NavigationSubcomponent :
    NavigationProvider,
    AppCoroutineScopeProvider,
    FeatureEntriesProvider {
    val appViewModel: AppViewModel

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance navController: NavController): NavigationSubcomponent
    }
}

@Module(subcomponents = [NavigationSubcomponent::class])
interface NavigationSubcomponentModule