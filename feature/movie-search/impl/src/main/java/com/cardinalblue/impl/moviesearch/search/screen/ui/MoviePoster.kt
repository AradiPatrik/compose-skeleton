package com.cardinalblue.impl.moviesearch.search.screen.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.palette.graphics.Palette
import com.cardinalblue.movie_search.impl.R
import com.cardinalblue.theme.SkeletonTheme
import com.cardinalblue.theme.modifier.glow
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.crossfade.CrossfadePlugin
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.palette.PalettePlugin
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin

@Composable
fun Poster(modifier: Modifier = Modifier, url: String, movieId: Int, onPosterClick: (Int) -> Unit) {
    var paletteState by rememberPaletteState()
    val animatedColor by animateColorAsState(
        targetValue = paletteState?.dominantSwatch?.rgb?.let(::Color)
            ?: MaterialTheme.colorScheme.surfaceColorAtElevation(4.dp),
        animationSpec = tween(1500)
    )
    Box(
        modifier = modifier
            .height(255.dp)
            .padding(8.dp)
            .glow(color = animatedColor, alpha = 0.5f, radius = 25.dp, offsetY = 12.dp)
    ) {
        GlideImage(
            modifier = Modifier
                .clip(MaterialTheme.shapes.medium)
                .clickable { onPosterClick(movieId) },
            imageModel = { url },
            imageOptions = ImageOptions(
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            ),
            component = rememberImageComponent {
                +ShimmerPlugin(
                    baseColor = MaterialTheme.colorScheme.surface,
                    highlightColor = MaterialTheme.colorScheme.surfaceColorAtElevation(4.dp),
                )
                +CrossfadePlugin(duration = 500)
                +PalettePlugin { palette -> paletteState = palette }
            },
            previewPlaceholder = com.cardinalblue.theme.R.drawable.tom_and_jerry,
        )
    }

}

@Composable
private fun rememberPaletteState(initialPalette: Palette? = null) = remember {
    mutableStateOf(initialPalette)
}

@Preview(showBackground = true)
@Composable
fun PosterPreview() {
    SkeletonTheme {
        Poster(
            url = "http://image.tmdb.org/t/p/w500/9Rj8l6gElLpRL7Kj17iZhrT5Zuw.jpg",
            movieId = 0,
            onPosterClick = {},
        )
    }
}