package com.cardinalblue.impl.moviesearch.search.di

import com.cardinalblue.impl.moviesearch.search.screen.SearchScreenViewModel
import com.cardinalblue.navigation.SubfeatureScoped
import dagger.Subcomponent

/**
 * The component for the screen inside the feature. It is providing the screen-wide dependencies.
 * If the sub-feature needs dependencies other than the ones provided in the root component, it
 * should be added inside the [SearchSubcomponent.Factory] as parameter.
 */
@SubfeatureScoped
@Subcomponent(modules = [SearchModule::class])
interface SearchSubcomponent {
    val viewModelFactory: SearchScreenViewModel.Factory
    @Subcomponent.Factory
    interface Factory {
        fun create(): SearchSubcomponent
    }
}