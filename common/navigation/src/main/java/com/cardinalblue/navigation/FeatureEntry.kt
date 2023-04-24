package com.cardinalblue.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface FeatureEntry {

    val featureRoute: String

    val arguments: List<NamedNavArgument>
        get() = emptyList()

    val deepLinks: List<NavDeepLink>
        get() = emptyList()
}

interface AggregateFeatureEntry : FeatureEntry {
    fun NavGraphBuilder.navigation(navController: NavHostController)
}