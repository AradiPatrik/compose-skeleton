package com.cardinalblue.navigation

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.cardinalblue.platform.serialize
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import logcat.LogPriority
import logcat.logcat
import javax.inject.Inject

interface NavigationManager {
    fun navigate(
        directions: NavigationCommand,
        navigationOptions: NavigationOptions = NavigationOptions()
    )

    val commands: SharedFlow<NavigationEvent>

    fun navigateBack()

    fun setResult(key: String, value: String)

    val currentBackStackEntryFlow: Flow<NavBackStackEntry>
}

inline fun <reified T> NavigationManager.setResult(value: T) where T : NavOutput, T : OutputType<T> {
    setResult(value.key, value.serialize())
}

data class NavigationOptions(
    val popUpToThis: Boolean = false,
)

data class NavigationEvent(
    val command: NavigationCommand,
    val options: NavigationOptions = NavigationOptions(),
)

class NavigationManagerImpl @Inject constructor(private val navController: NavController) :
    NavigationManager {
    private val _commands =
        MutableSharedFlow<NavigationEvent>(extraBufferCapacity = 1, replay = 1)
    override val commands = _commands.asSharedFlow()

    override val currentBackStackEntryFlow = navController.currentBackStackEntryFlow

    override fun navigate(directions: NavigationCommand, navigationOptions: NavigationOptions) {
        _commands.tryEmit(NavigationEvent(directions, navigationOptions))
    }

    override fun navigateBack() {
        _commands.tryEmit(NavigationEvent(BackCommand))
    }

    override fun setResult(key: String, value: String) {
        navController.previousBackStackEntry?.savedStateHandle?.set(key, value) ?: logcat(
            LogPriority.ERROR
        ) { "Something is missing" }
    }
}

fun NavigationManager.navigate(featureEntry: NavigationCommandProvider<EmptyInput>) {
    navigate(featureEntry.destination())
}

fun NavigationManager.confirm() {
    navigate(ConfirmCommand)
}
