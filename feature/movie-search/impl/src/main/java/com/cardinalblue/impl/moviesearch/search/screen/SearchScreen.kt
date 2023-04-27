package com.cardinalblue.impl.moviesearch.search.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.cardinalblue.domain.Movie
import com.cardinalblue.impl.moviesearch.search.screen.ui.MovieSearchBar
import com.cardinalblue.impl.moviesearch.search.screen.ui.MovieSearchGrid
import com.cardinalblue.impl.moviesearch.search.screen.ui.Poster
import com.cardinalblue.theme.SkeletonTheme
import com.cardinalblue.theme.modifier.glow
import kotlinx.coroutines.flow.flowOf

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
