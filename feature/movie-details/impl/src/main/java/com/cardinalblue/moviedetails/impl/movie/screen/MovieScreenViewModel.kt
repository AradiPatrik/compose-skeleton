package com.cardinalblue.moviedetails.impl.movie.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cardinalblue.moviedetails.impl.di.MovieId
import com.cardinalblue.moviedetails.impl.movie.usecase.DoExample
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieScreenViewModel @Inject constructor(
    private val doExample: DoExample,
    @MovieId private val movieId: Int,
) : ViewModel() {
    val movieIdFlow = MutableStateFlow(movieId)
}