package com.cardinalblue.navigation

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.cardinalblue.platform.serialize
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import logcat.LogPriority
import logcat.logcat
import javax.inject.Inject
import javax.inject.Singleton

interface NavigationManager {
    fun navigate(directions: NavigationCommand)

    val commands: SharedFlow<NavigationCommand>

    fun navigateBack()

    fun setResult(key: String, value: String)

    val currentBackStackEntryFlow: Flow<NavBackStackEntry>
}

interface NavigationManagerFactory {
    fun create(navController: NavController): NavigationManager
}

class NavigationManagerFactoryImpl @Inject constructor() : NavigationManagerFactory {
    override fun create(navController: NavController): NavigationManager {
        return NavigationManagerImpl().apply {
            this.navController = navController
        }
    }
}

inline fun <reified T> NavigationManager.setResult(value: T) where T : NavOutput, T : OutputType<T> {
    setResult(value.key, value.serialize())
}

@Singleton
class NavigationManagerImpl @Inject constructor() : NavigationManager {
    private val _commands = MutableSharedFlow<NavigationCommand>(extraBufferCapacity = 1, replay = 1)
    override val commands = _commands.asSharedFlow()
    private val navControllerFlow = MutableStateFlow<NavController?>(null)
    var navController
        set(value) {
            logcat("Navigation") { "Nav controller set $value" }
            navControllerFlow.tryEmit(value)
        }
        get() = navControllerFlow.value

    override val currentBackStackEntryFlow = navControllerFlow.filterNotNull().flatMapLatest {
        it.currentBackStackEntryFlow
    }

    override fun navigate(directions: NavigationCommand) {
        _commands.tryEmit(directions)
    }

    override fun navigateBack() {
        _commands.tryEmit(BackCommand)
    }

    override fun setResult(key: String, value: String) {
        navController?.previousBackStackEntry?.savedStateHandle?.set(key, value) ?: logcat(
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
