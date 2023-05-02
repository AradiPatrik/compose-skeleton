package com.cardinalblue.impl.moviesearch.search.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.cardinalblue.domain.Movie
import com.cardinalblue.impl.moviesearch.search.screen.ui.MovieSearchBar
import com.cardinalblue.impl.moviesearch.search.screen.ui.MovieSearchGrid

@Composable
fun SearchScreen(viewModel: SearchScreenViewModel) {
    val pagingItems = viewModel.movies.collectAsLazyPagingItems()
    val query by viewModel.query.collectAsState()
    SearchScreen(pagingItems, query.query, viewModel::onMovieClick, viewModel::onQueryChange)
}

@Composable
fun SearchScreen(
    movies: LazyPagingItems<Movie>,
    query: String,
    onPosterClick: (Int) -> Unit,
    onQueryChange: (String) -> Unit
) {
    Column(Modifier.fillMaxSize()) {
        Surface {
            MovieSearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                query = query,
                onQueryChange = onQueryChange,
            )
        }

        MovieSearchGrid(movies = movies, onPosterClick = onPosterClick)
    }
}
