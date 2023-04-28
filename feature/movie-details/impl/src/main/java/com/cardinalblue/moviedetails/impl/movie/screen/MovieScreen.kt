package com.cardinalblue.moviedetails.impl.movie.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cardinalblue.domain.Movie
import com.cardinalblue.theme.SkeletonTheme

@Composable
fun MovieScreen(viewModel: MovieScreenViewModel) {
    val movie by viewModel.movie.collectAsState()
    movie?.let {
        MovieScreen(it)
    }
}

@Composable
fun MovieScreen(movie: Movie) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp),
                text = "Movie Screen: ${movie.title}",
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MovieScreenPreview() {
    SkeletonTheme {
        MovieScreen(
            Movie(
                1,
                "Movie Title",
                "Movie Overview",
                "1.0",
                "Movie Poster",
                "Movie Backdrop",
                "1.0",
                1.0f,
                10
            )
        )
    }
}