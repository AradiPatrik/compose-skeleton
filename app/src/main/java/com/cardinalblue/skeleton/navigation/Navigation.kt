package com.cardinalblue.skeleton.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.cardinalblue.api.DataProvider
import com.cardinalblue.api.MovieSearchFeatureEntry
import com.cardinalblue.navigation.CompositionLocals
import com.cardinalblue.navigation.FeatureEntriesProvider
import com.cardinalblue.navigation.find

@Composable
fun App(navController: NavController) {
    Scaffold(
        bottomBar = { MovieDbBottomAppBar() },
        content = { paddingValues ->
            NavGraph(paddingValues, navController)
        }
    )
}

@Composable
fun NavGraph(paddingValues: PaddingValues, navController: NavController) {
    val featureEntries = CompositionLocals.current<FeatureEntriesProvider>().featureEntries
    val movieSearchEntry = featureEntries.find<MovieSearchFeatureEntry>()

    Box(modifier = Modifier.fillMaxSize()) {
        NavHost(
            navController as NavHostController,
            startDestination = "@movie-search"
        ) {
            with(movieSearchEntry) {
                navigation(navController)
            }
        }
    }
}

