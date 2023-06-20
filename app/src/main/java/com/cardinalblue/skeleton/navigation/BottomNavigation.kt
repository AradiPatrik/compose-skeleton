package com.cardinalblue.skeleton.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    val offset by animateDpAsState(targetValue = if (selectedTab != None) 0.dp else 80.dp, tween(700, 700))
    val height by animateDpAsState(targetValue = if (selectedTab != None) 80.dp else 0.dp, tween(700, 700))
    Box(modifier = Modifier.fillMaxWidth().height(height).offset(y = offset)) {
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