package com.cardinalblue.skeleton.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import com.cardinalblue.featuredmovies.api.FeaturedMoviesFeatureEntry
import com.cardinalblue.moviedetails.api.MovieDetailsFeatureEntry
import com.cardinalblue.moviesearch.api.MovieSearchFeatureEntry
import com.cardinalblue.navigation.CompositionLocals
import com.cardinalblue.navigation.FeatureEntriesProvider
import com.cardinalblue.navigation.NavigationEvent
import com.cardinalblue.navigation.ToDestinationCommand
import com.cardinalblue.navigation.addFeatureEntry
import com.cardinalblue.navigation.find
import com.cardinalblue.profile.api.ProfileFeatureEntry
import com.cardinalblue.skeleton.AppViewModel

@Composable
fun App(navController: NavController, appViewModel: AppViewModel) {
    Scaffold(
        bottomBar = { MovieDbBottomAppBar(appViewModel) },
        content = { paddingValues ->
            NavGraph(paddingValues, navController)
        }
    )
}

@Composable
fun NavGraph(paddingValues: PaddingValues, navController: NavController) {
    val featureEntries = CompositionLocals.current<FeatureEntriesProvider>().featureEntries
    val movieSearchEntry = featureEntries.find<MovieSearchFeatureEntry>()
    val profileEntry = featureEntries.find<ProfileFeatureEntry>()
    val featuredMoviesEntry = featureEntries.find<FeaturedMoviesFeatureEntry>()
    val movieDetailsFeatureEntry = featureEntries.find<MovieDetailsFeatureEntry>()

    val routeDestinations = remember {
        listOf(
            FeaturedMoviesFeatureEntry.featureRoute,
            MovieSearchFeatureEntry.featureRoute,
            ProfileFeatureEntry.featureRoute
        )
    }

    val navigate: NavHostController.(command: NavigationEvent) -> Unit  = { event ->
        val command = event.command
        if (command is ToDestinationCommand) {
            val navOptions = if (command.destination in routeDestinations) {
                NavOptions.Builder()
                    .setPopUpTo(
                        destinationId = navController.graph.findStartDestination().id,
                        inclusive = false,
                        saveState = true
                    )
                    .setLaunchSingleTop(true)
                    .setRestoreState(true)
                    .build()
            } else {
                null
            }
            navController.navigate(command.destination, navOptions = navOptions)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        NavHost(
            navController as NavHostController,
            startDestination = "@movie-search"
        ) {
            addFeatureEntry(navController, movieSearchEntry, navigate)
            addFeatureEntry(navController, profileEntry, navigate)
            addFeatureEntry(navController, featuredMoviesEntry, navigate)
            addFeatureEntry(navController, movieDetailsFeatureEntry, navigate)
        }
    }
}

