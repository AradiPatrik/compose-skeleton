package com.cardinalblue.impl.moviesearch.search.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.cardinalblue.domain.Movie
import com.cardinalblue.impl.moviesearch.search.usecase.SearchMovies
import com.cardinalblue.moviedetails.api.MovieDetailsFeatureEntry
import com.cardinalblue.moviedetails.api.MovieDetailsInput
import com.cardinalblue.navigation.NavigationManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import logcat.logcat
import javax.inject.Inject

class SearchScreenViewModel @Inject constructor(
    private val searchMovies: SearchMovies,
    private val navigationManager: NavigationManager,
) : ViewModel() {
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
    }

    val movies: Flow<PagingData<Movie>> = query
        .debounce { if (it is Query.Typing) 500 else 0 }
        .flatMapLatest { searchMovies(it.query) }
        .cachedIn(viewModelScope)

    fun onQueryChange(newSearchQuery: String) {
        _query.value = Query.Typing(newSearchQuery)
    }

    fun refresh() {
        _query.value = Query.ForceRefresh(query.value.query)
    }

    fun onMovieClick(movieId: Int) {
        navigationManager.navigate(
            MovieDetailsFeatureEntry.destination(MovieDetailsInput(movieId)))
    }
}