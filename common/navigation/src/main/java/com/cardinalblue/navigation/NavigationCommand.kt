package com.cardinalblue.navigation

import androidx.navigation.NamedNavArgument

interface NavigationCommand {
    val arguments: List<NamedNavArgument>

    val destinationFeatureRoute: String

    val destination: String
}
