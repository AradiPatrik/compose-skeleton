package com.cardinalblue.impl.di

import com.cardinalblue.api.DataProvider
import com.cardinalblue.impl.screen.search.SearchScreenViewModel
import com.cardinalblue.navigation.FeatureScoped
import com.cardinalblue.platform.PlatformProvider
import dagger.Component

@FeatureScoped
@Component(
    dependencies = [
        DataProvider::class,
        PlatformProvider::class,
    ],
    modules = [MovieSearchModule::class]
)
interface MovieSearchComponent {
    val viewModel: SearchScreenViewModel
}