package com.cardinalblue.navigation

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface RootComponentHolder<C : NavigationProvider> {

    val rootRoute: String

    @Composable
    fun rootComponent(
        currentEntry: NavBackStackEntry,
        navController: NavHostController,
    ): C {
        return rootComponent(currentEntry, navController, currentEntry.arguments)
    }

    @Composable
    fun rootComponent(
        currentEntry: NavBackStackEntry,
        navController: NavHostController,
        arguments: Bundle?
    ): C {
        val rootEntry = currentEntry.rememberBackStackEntry(navController, route = rootRoute)
        return provideRootComponent(rootEntry, navController, arguments)
    }

    @Composable
    fun provideRootComponent(
        rootEntry: NavBackStackEntry,
        navController: NavController,
        arguments: Bundle?
    ): C
}