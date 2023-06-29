package com.cardinalblue.profile.impl.profiledetails.di

import com.cardinalblue.navigation.NavigationManagerModule
import com.cardinalblue.navigation.NavigationProvider
import com.cardinalblue.navigation.SubfeatureSubcomponentFactory
import com.cardinalblue.profile.impl.profiledetails.screen.ProfileDetailsScreenViewModel
import com.cardinalblue.navigation.SubfeatureScoped
import dagger.Subcomponent

/**
 * The component for the screen inside the feature. It is providing the screen-wide dependencies.
 * If the sub-feature needs dependencies other than the ones provided in the root component, it
 * should be added inside the [ProfileDetailsSubcomponent.Factory] as parameter.
 */
@SubfeatureScoped
@Subcomponent(
    modules = [
        ProfileDetailsModule::class,
        NavigationManagerModule::class
    ]
)
interface ProfileDetailsSubcomponent : NavigationProvider {
    val viewModelFactory: ProfileDetailsScreenViewModel.Factory

    @Subcomponent.Factory
    interface Factory : SubfeatureSubcomponentFactory<ProfileDetailsSubcomponent>
}