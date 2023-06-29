package com.cardinalblue.featuredmovies.impl.featuredmovieslist.screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cardinalblue.featuredmovies.impl.featuredmovieslist.usecase.DoExample
import com.cardinalblue.navigation.AssistedViewModelFactory
import com.cardinalblue.navigation.EmptyInput
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class FeaturedMoviesListScreenViewModel @AssistedInject constructor(
    private val doExample: DoExample,
    @Assisted private val emptyInput: EmptyInput,
    @Assisted private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    @AssistedFactory
    interface Factory : AssistedViewModelFactory<EmptyInput, FeaturedMoviesListScreenViewModel>

    @Suppress("unused")
    fun onClick() = viewModelScope.launch {
        doExample("example")
    }
}