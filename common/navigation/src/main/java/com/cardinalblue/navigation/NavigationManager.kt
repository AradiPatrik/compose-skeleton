package com.cardinalblue.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationManager @Inject constructor(){
    val commands = MutableSharedFlow<NavigationCommand>(extraBufferCapacity = 1)

    fun navigate(directions: NavigationCommand) {
        commands.tryEmit(directions)
    }
}

fun NavigationManager.navigate(featureEntry: NavigationCommandProvider<EmptyInput>) {
    navigate(featureEntry.destination())
}
