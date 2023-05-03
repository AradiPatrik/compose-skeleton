package com.cardinalblue.moviedetails.impl.movie.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.cardinalblue.domain.Author
import com.cardinalblue.domain.Movie
import com.cardinalblue.domain.Review
import com.cardinalblue.theme.R
import com.cardinalblue.theme.SkeletonTheme
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.crossfade.CrossfadePlugin
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin
import java.time.Instant
import java.util.Date

@Composable
fun MovieScreen(viewModel: MovieScreenViewModel) {
    val movie by viewModel.movie.collectAsState()
    val reviews = viewModel.reviews.collectAsLazyPagingItems()
    movie?.let {
        MovieScreen(it, viewModel::onCreditsClicked, reviews)
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

@Composable
fun MovieScreen(movie: Movie, onCreditsClick: () -> Unit, reviews: LazyPagingItems<Review>) {
    Column(Modifier.fillMaxSize()) {
        LazyColumn {
            item {
                Header(movie, onCreditsClick)
            }
            items(reviews.itemCount) { index ->
                reviews[index]?.let { review ->
                    ReviewItem(review = review)
                }
            }
        }
    }
}

@Composable
private fun Header(movie: Movie, onCreditsClick: () -> Unit) {
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

@Composable
private fun ReviewItem(review: Review, modifier: Modifier = Modifier) {
    OutlinedCard(
        modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        ListItem(
            headlineContent = { Text(text = review.author.name.ifBlank { review.author.username }) },
            leadingContent = {
                if (review.author.avatarPath != null) {
                    GlideImage(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(CircleShape),
                        imageModel = { review.author.avatarPath },
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = review.author.username.first().toString(),
                            style = MaterialTheme.typography.headlineMedium,
                        )
                    }
                }
            },
            supportingContent = {
                Text(text = review.content, maxLines = 2, overflow = TextOverflow.Ellipsis)
            },
        )
    }

}

@Composable
@Preview
private fun ReviewItemPreview() {
    SkeletonTheme {
        ReviewItem(
            review = Review(
                "asdf",
                Author("Patrik", "aradi", "something", 4.0f),
                "This was a good movie",
                Date.from(Instant.now()),
                Date.from(Instant.now())
            )
        )
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
