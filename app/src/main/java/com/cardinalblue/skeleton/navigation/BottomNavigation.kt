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
import androidx.compose.ui.tooling.preview.Preview
import com.cardinalblue.theme.SkeletonTheme

@Composable
internal fun MovieDbBottomAppBar() {
    NavigationBar {
        NavigationBarItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Icon(Icons.Filled.Home, contentDescription = null)

            },
            label = {
                Text(text = "Home")
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Icon(Icons.Filled.Star, contentDescription = null)
            },
            label = {
                Text(text = "Featured")
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Icon(Icons.Filled.AccountCircle, contentDescription = null)
            },
            label = {
                Text(text = "Account")
            }
        )
    }
}

@Preview
@Composable
private fun BottomNavigationPreview() {
    SkeletonTheme {
        MovieDbBottomAppBar()
    }
}