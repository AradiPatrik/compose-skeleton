package com.cardinalblue.moviedetails.impl.credits.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.cardinalblue.domain.Credit
import com.cardinalblue.theme.SkeletonTheme

@Composable
fun CreditsScreen(viewModel: CreditsScreenViewModel) {
    val credits by viewModel.credits.collectAsState()
    CreditsScreen(credits)
}

@Composable
fun CreditsScreen(credits: List<Credit>) {
    Text(text = "Credits Screen")

}
