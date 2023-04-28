package com.cardinalblue.navigation

import androidx.navigation.NavController
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject
import javax.inject.Singleton

interface NavigationManager {
    val backStack: SharedFlow<List<String>>
    fun navigate(directions: NavigationCommand)
}

@Singleton
class NavigationManagerImpl @Inject constructor() : NavigationManager {
    val commands = MutableSharedFlow<NavigationCommand>(extraBufferCapacity = 1)
    lateinit var navController: NavController

    /**
     * The lifetime of this flow is the lifetime of the application
     * so it's safe to share it in the [GlobalScope]
     */
    @OptIn(DelicateCoroutinesApi::class)
    override val backStack
        get() = navController.currentBackStackEntryFlow
            .map { navController.backQueue.map { it.destination.route.orEmpty() } }
            .shareIn(GlobalScope, SharingStarted.Eagerly, replay = 1)

    override fun navigate(directions: NavigationCommand) {
        commands.tryEmit(directions)
    }
}

fun NavigationManager.navigate(featureEntry: NavigationCommandProvider<EmptyInput>) {
    navigate(featureEntry.destination())
}
