package com.cardinalblue.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

interface Input

object EmptyInput : Input

interface FeatureEntry<I : Input> {
    val featureRoute: String

    fun destination(input: I): NavigationCommand

    val arguments: List<NamedNavArgument>

    fun NavGraphBuilder.navigation(navController: NavHostController)
}

fun FeatureEntry<EmptyInput>.destination(): NavigationCommand = destination(EmptyInput)

fun NavGraphBuilder.addFeatureEntry(
    navController: NavHostController,
    featureEntry: FeatureEntry<*>,
) {
    with(featureEntry) {
        navigation(navController)
    }
}