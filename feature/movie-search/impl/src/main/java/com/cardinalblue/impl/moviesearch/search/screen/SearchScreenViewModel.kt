package com.cardinalblue.impl.moviesearch.search.screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.cardinalblue.domain.Movie
import com.cardinalblue.featuredmovies.api.FeaturedMoviesFeatureEntry
import com.cardinalblue.impl.moviesearch.search.usecase.SearchMovies
import com.cardinalblue.moviedetails.api.MovieDetailsFeatureEntry
import com.cardinalblue.moviedetails.api.MovieDetailsInput
import com.cardinalblue.moviedetails.api.MovieDetailsOutput
import com.cardinalblue.navigation.AssistedViewModelFactory
import com.cardinalblue.navigation.EmptyInput
import com.cardinalblue.navigation.NavigationManager
import com.cardinalblue.navigation.destination
import com.cardinalblue.navigation.getOutputFlow
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import logcat.logcat

class SearchScreenViewModel @AssistedInject constructor(
    private val searchMovies: SearchMovies,
    private val navigationManager: NavigationManager,
    @Assisted private val savedStateHandle: SavedStateHandle,
    @Assisted private val emptyInput: EmptyInput
) : ViewModel() {
    @AssistedFactory
    interface Factory : AssistedViewModelFactory<EmptyInput, SearchScreenViewModel>

    sealed interface Query {
        val query: String

        @JvmInline
        value class Typing(override val query: String) : Query

        @JvmInline
        value class ForceRefresh(override val query: String) : Query
    }

    private val _query = MutableStateFlow<Query>(Query.ForceRefresh(""))
    val query = _query.asStateFlow()

    init {
        _query.value = Query.Typing("Spiderman")
        savedStateHandle.getOutputFlow(MovieDetailsOutput)
            .onEach {
                logcat("APDEBUG") { "Navigating with $navigationManager"}
                navigationManager.navigate(FeaturedMoviesFeatureEntry.destination())
            }
            .launchIn(viewModelScope)
    }

    val movies: Flow<PagingData<Movie>> = query
        .debounce { if (it is Query.Typing) 500 else 0 }
        .flatMapLatest { searchMovies(it.query) }
        .cachedIn(viewModelScope)

    fun onQueryChange(newSearchQuery: String) {
        _query.value = Query.Typing(newSearchQuery)
    }

    fun onMovieClick(movieId: Int) {
        navigationManager.navigate(
            MovieDetailsFeatureEntry.destination(MovieDetailsInput(movieId))
        )
    }
}