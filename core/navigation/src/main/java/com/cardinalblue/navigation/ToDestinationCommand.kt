package com.cardinalblue.navigation

import androidx.navigation.NamedNavArgument

sealed interface NavigationCommand

interface ToDestinationCommand : NavigationCommand {
    // movie-id: MovieId
    val args: List<NamedNavArgument>

    // movie-details/{movie-id}
    val destinationFeatureRoute: String

    // movie-details/{ "id": 123 }
    val destination: String
}

object BackCommand : NavigationCommand

object ConfirmCommand : NavigationCommand