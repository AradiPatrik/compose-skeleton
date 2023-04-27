package com.cardinalblue.skeleton.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.cardinalblue.skeleton.AppViewModel
import com.cardinalblue.skeleton.AppViewModel.BottomSheetSelectedTab
import com.cardinalblue.skeleton.AppViewModel.BottomSheetSelectedTab.FeaturedMovies
import com.cardinalblue.skeleton.AppViewModel.BottomSheetSelectedTab.MovieSearch
import com.cardinalblue.skeleton.AppViewModel.BottomSheetSelectedTab.None
import com.cardinalblue.skeleton.AppViewModel.BottomSheetSelectedTab.Profile
import com.cardinalblue.theme.SkeletonTheme

@Composable
internal fun MovieDbBottomAppBar(viewModel: AppViewModel) {
    val selectedTab by viewModel.selectedTab.collectAsState()
    MovieDbBottomAppBar(selectedTab, viewModel::navigateTo)
}

@Composable
private fun MovieDbBottomAppBar(
    selectedTab: BottomSheetSelectedTab,
    onBottomSheetTabSelected: (BottomSheetSelectedTab) -> Unit
) {
    if (selectedTab != None) {
        NavigationBar {
            NavigationBarItem(
                selected = selectedTab == MovieSearch,
                onClick = { onBottomSheetTabSelected(MovieSearch) },
                icon = {
                    Icon(Icons.Filled.Home, contentDescription = null)

                },
                label = {
                    Text(text = "Home")
                }
            )
            NavigationBarItem(
                selected = selectedTab == FeaturedMovies,
                onClick = { onBottomSheetTabSelected(FeaturedMovies) },
                icon = {
                    Icon(Icons.Filled.Star, contentDescription = null)
                },
                label = {
                    Text(text = "Featured")
                }
            )
            NavigationBarItem(
                selected = selectedTab == Profile,
                onClick = { onBottomSheetTabSelected(Profile) },
                icon = {
                    Icon(Icons.Filled.AccountCircle, contentDescription = null)
                },
                label = {
                    Text(text = "Account")
                }
            )
        }
    }
}

@Preview
@Composable
fun MovieDbBottomAppBarPreview() {
    SkeletonTheme {
        MovieDbBottomAppBar(
            selectedTab = MovieSearch,
            onBottomSheetTabSelected = {},
        )
    }
}