package com.cardinalblue.impl.moviesearch.search.screen.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cardinalblue.theme.SkeletonTheme

@Composable
fun MovieSearchBar(query: String, onQueryChange: (String) -> Unit, modifier: Modifier = Modifier) {
    OutlinedTextField(
        modifier = modifier,
        value = query,
        label = { Text("Search") },
        onValueChange = onQueryChange,
    )
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES, backgroundColor = 0xFF000000)
fun MovieSearchBarPreview() {
    SkeletonTheme {
        MovieSearchBar(
            query = "Spiderman",
            onQueryChange = { },
        )
    }
}