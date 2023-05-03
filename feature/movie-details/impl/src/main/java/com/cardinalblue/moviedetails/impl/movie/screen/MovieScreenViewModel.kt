package com.cardinalblue.moviedetails.impl.movie.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.cardinalblue.domain.Movie
import com.cardinalblue.domain.Review
import com.cardinalblue.moviedetails.api.MovieDetailsInput
import com.cardinalblue.moviedetails.impl.common.reviews.usecase.GetReviews
import com.cardinalblue.moviedetails.impl.di.MovieId
import com.cardinalblue.moviedetails.impl.directions.MovieDetailsDirections
import com.cardinalblue.moviedetails.impl.directions.MovieDetailsDirections.Credits
import com.cardinalblue.moviedetails.impl.movie.usecase.GetMovie
import com.cardinalblue.navigation.NavigationManager
import com.cardinalblue.navigation.SubfeatureScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@SubfeatureScoped
class MovieScreenViewModel @Inject constructor(
    private val getMovie: GetMovie,
    private val getReviews: GetReviews,
    @MovieId private val movieId: Int,
    private val navigationManager: NavigationManager,
) : ViewModel() {
    val movie = MutableStateFlow<Movie?>(null)
    val reviews = getReviews(movieId).cachedIn(viewModelScope)

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
