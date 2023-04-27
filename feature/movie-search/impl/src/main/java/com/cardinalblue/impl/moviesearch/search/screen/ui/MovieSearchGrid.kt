package com.cardinalblue.impl.moviesearch.search.screen.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.cardinalblue.domain.Movie

@Composable
fun MovieSearchGrid(movies: LazyPagingItems<Movie>, onPosterClick: (Int) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3), Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 8.dp)
    ) {
        items(movies.itemCount) { index ->
            movies[index]?.let { movie ->
                Poster(url = movie.posterUrl!!, movieId = movie.id, onPosterClick = onPosterClick)
            }
        }
    }
}