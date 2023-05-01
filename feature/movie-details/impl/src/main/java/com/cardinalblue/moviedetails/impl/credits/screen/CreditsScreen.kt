package com.cardinalblue.moviedetails.impl.credits.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.cardinalblue.theme.SkeletonTheme

@Composable
fun CreditsScreen(viewModel: CreditsScreenViewModel) {
    CreditsScreen()
}

@Composable
fun CreditsScreen() {
    Text(text = "Credits Screen")
}

@Composable
@Preview
fun CreditsScreenPreview() {
    SkeletonTheme {
        CreditsScreen()
    }
}
