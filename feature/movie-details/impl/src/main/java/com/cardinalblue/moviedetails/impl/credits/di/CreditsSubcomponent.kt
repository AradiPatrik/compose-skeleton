package com.cardinalblue.moviedetails.impl.credits.di

import com.cardinalblue.moviedetails.impl.credits.screen.CreditsScreenViewModel
import com.cardinalblue.navigation.NavigationManagerModule
import com.cardinalblue.navigation.NavigationProvider
import com.cardinalblue.navigation.SubfeatureScoped
import dagger.Subcomponent

@SubfeatureScoped
@Subcomponent(
    modules = [
        CreditsModule::class,
        NavigationManagerModule::class,
    ]
)
interface CreditsSubcomponent : NavigationProvider {
    val viewModelFactory: CreditsScreenViewModel.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(): CreditsSubcomponent
    }
}