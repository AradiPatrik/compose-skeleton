package com.cardinalblue.profile.impl.profiledetails.di

import com.cardinalblue.profile.impl.profiledetails.screen.ProfileDetailsScreenViewModel
import com.cardinalblue.navigation.SubfeatureScoped
import dagger.Subcomponent

/**
 * The component for the screen inside the feature. It is providing the screen-wide dependencies.
 * If the sub-feature needs dependencies other than the ones provided in the root component, it
 * should be added inside the [ProfileDetailsSubcomponent.Factory] as parameter.
 */
@SubfeatureScoped
@Subcomponent(modules = [ProfileDetailsModule::class])
interface ProfileDetailsSubcomponent {
    val viewModel: ProfileDetailsScreenViewModel

    @Subcomponent.Factory
    interface Factory {
        fun create(): ProfileDetailsSubcomponent
    }
}