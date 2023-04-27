package com.cardinalblue.featuredmovies.impl.featuredmovieslist.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cardinalblue.featuredmovies.impl.featuredmovieslist.usecase.DoExample
import kotlinx.coroutines.launch
import javax.inject.Inject

class FeaturedMoviesListScreenViewModel @Inject constructor(
    private val doExample: DoExample,
) : ViewModel() {
    fun onClick() = viewModelScope.launch {
        doExample("example")
    }
}