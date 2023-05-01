package com.cardinalblue.moviedetails.impl.movie.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cardinalblue.domain.Movie
import com.cardinalblue.moviedetails.api.MovieDetailsInput
import com.cardinalblue.moviedetails.impl.di.MovieId
import com.cardinalblue.moviedetails.impl.directions.MovieDetailsDirections
import com.cardinalblue.moviedetails.impl.directions.MovieDetailsDirections.Credits
import com.cardinalblue.moviedetails.impl.movie.usecase.GetMovie
import com.cardinalblue.navigation.NavigationManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieScreenViewModel @Inject constructor(
    private val getMovie: GetMovie,
    @MovieId private val movieId: Int,
    private val navigationManager: NavigationManager,
) : ViewModel() {
    val movie = MutableStateFlow<Movie?>(null)

    init {
        viewModelScope.launch {
            movie.value = getMovie(movieId)
        }
    }

    fun onCreditsClicked() {
        navigationManager.navigate(
            Credits.destination(MovieDetailsInput(movieId))
        )
    }
}
