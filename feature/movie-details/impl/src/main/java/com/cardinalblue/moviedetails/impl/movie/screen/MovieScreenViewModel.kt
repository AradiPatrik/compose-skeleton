package com.cardinalblue.moviedetails.impl.movie.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cardinalblue.domain.Movie
import com.cardinalblue.moviedetails.impl.di.MovieId
import com.cardinalblue.moviedetails.impl.movie.usecase.GetMovie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieScreenViewModel @Inject constructor(
    private val getMovie: GetMovie,
    @MovieId private val movieId: Int,
) : ViewModel() {
    val movie = MutableStateFlow<Movie?>(null)

    init {
        viewModelScope.launch {
            movie.value = getMovie(movieId)
        }
    }
}
