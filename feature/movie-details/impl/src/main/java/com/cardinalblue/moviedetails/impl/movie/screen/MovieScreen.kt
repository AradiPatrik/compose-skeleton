package com.cardinalblue.moviedetails.impl.movie.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cardinalblue.domain.Movie
import com.cardinalblue.theme.R
import com.cardinalblue.theme.SkeletonTheme
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.crossfade.CrossfadePlugin
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin

@Composable
fun MovieScreen(viewModel: MovieScreenViewModel) {
    val movie by viewModel.movie.collectAsState()
    movie?.let {
        MovieScreen(it, viewModel::onCreditsClicked)
    } ?: FullScreenLoading()
}

@Composable
private fun FullScreenLoading() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun FullScreenLoadingPreview() {
    SkeletonTheme {
        FullScreenLoading()
    }
}

@Composable
fun MovieScreen(movie: Movie, onCreditsClick: () -> Unit) {
    Column(Modifier.fillMaxSize()) {
        BackDropImage(movie.backdropUrl)
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = movie.title,
            style = MaterialTheme.typography.headlineMedium,
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = movie.overview,
            style = MaterialTheme.typography.bodyMedium,
        )
        TextButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            onClick = onCreditsClick,
        ) {
            Text(text = "Show credits")
        }
    }
}

@Composable
private fun BackDropImage(backdropUrl: String?) {
    GlideImage(
        modifier = Modifier
            .fillMaxWidth(),
        imageModel = { backdropUrl },
        imageOptions = ImageOptions(
            contentScale = ContentScale.Inside,
            alignment = Alignment.Center
        ),
        component = rememberImageComponent {
            +ShimmerPlugin(
                baseColor = MaterialTheme.colorScheme.surface,
                highlightColor = MaterialTheme.colorScheme.surfaceColorAtElevation(4.dp),
            )
            +CrossfadePlugin(duration = 500)
        },
        previewPlaceholder = R.drawable.tom_and_jerry,
    )
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
            ),
            onCreditsClick = {}
        )
    }
}