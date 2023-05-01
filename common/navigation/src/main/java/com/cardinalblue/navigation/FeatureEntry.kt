package com.cardinalblue.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

interface Input

object EmptyInput : Input

interface FeatureEntry<I : Input> {
    fun NavGraphBuilder.navigation(navController: NavHostController)
}

interface NavigationCommandProvider<I : Input> {
    val featureRoute: String
    val arguments: List<NamedNavArgument>
    fun destination(input: I): NavigationCommand
}

fun NavigationCommandProvider<EmptyInput>.destination(): NavigationCommand = destination(EmptyInput)

fun NavGraphBuilder.addFeatureEntry(
    navController: NavHostController,
    featureEntry: FeatureEntry<*>,
) {
    with(featureEntry) {
        navigation(navController)
    }
}

fun NavGraphBuilder.composable(
    navigationCommandProvider: NavigationCommandProvider<*>,
    content: @Composable (NavBackStackEntry) -> Unit,
) {
    composable(
        route = navigationCommandProvider.featureRoute,
        arguments = navigationCommandProvider.arguments,
        content = content
    )
}