package com.cardinalblue.navigation

import androidx.navigation.NamedNavArgument

interface NavigationCommand {
    val args: List<NamedNavArgument>

    val destinationFeatureRoute: String

    val destination: String
}
