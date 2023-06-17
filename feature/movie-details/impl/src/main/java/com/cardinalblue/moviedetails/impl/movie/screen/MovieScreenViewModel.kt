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
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieScreenViewModel @AssistedInject constructor(
    private val getMovie: GetMovie,
    private val getReviews: GetReviews,
    @Assisted private val input: MovieDetailsInput,
    private val navigationManager: NavigationManager,
) : ViewModel() {
    @AssistedFactory
    interface Factory {
        fun create(input: MovieDetailsInput): MovieScreenViewModel
    }

    val movie = MutableStateFlow<Movie?>(null)
    val reviews = getReviews(input.movieId).cachedIn(viewModelScope)

    init {
        viewModelScope.launch {
            movie.value = getMovie(input.movieId)
        }
    }

    fun onCreditsClicked() {
        navigationManager.navigate(
            Credits.destination(MovieDetailsInput(input.movieId))
        )
    }
}
