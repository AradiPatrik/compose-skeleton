package com.cardinalblue.navigation

import android.os.Bundle
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterExitState
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.cardinalblue.platform.deserialize
import com.cardinalblue.platform.serialize
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import logcat.logcat

interface NavInput

interface InputType<I : NavInput> {
    val key: String
    val type: NavType<I>
}

object EmptyInput : NavInput, InputType<EmptyInput> {
    override val key: String get() = ""
    override val type: NavType<EmptyInput> = createNavType(isNullableAllowed = true)
}

interface NavOutput

interface OutputType<T : NavOutput> {
    val key: String
}

interface FeatureEntry<I : NavInput> {
    fun NavGraphBuilder.navigation(
        navController: NavHostController,
        navigate: NavHostController.(ToDestinationCommand) -> Unit
    )
}

abstract class BaseFeatureEntry<I : NavInput, C>(
    override val rootRoute: String,
    private val startRoute: String,
) : RootComponentHolder<C>, FeatureEntry<I> {
    override fun NavGraphBuilder.navigation(
        navController: NavHostController,
        navigate: NavHostController.(ToDestinationCommand) -> Unit
    ) {
        navigation(startDestination = startRoute, route = rootRoute) {
            buildNavigation(navController, navigate)
        }
    }

    abstract fun NavGraphBuilder.buildNavigation(
        navController: NavHostController,
        navigate: NavHostController.(ToDestinationCommand) -> Unit
    )

    inline fun <reified T : ViewModel, reified U : InputType<V>, reified V, W : NavigationProvider> NavGraphBuilder.addNode(
        direction: NavDirection<V>,
        navController: NavHostController,
        noinline navigate: NavHostController.(ToDestinationCommand) -> Unit,
        crossinline createSubcomponent: (C) -> W,
        crossinline createVm: (savedStateHandle: SavedStateHandle, input: I, component: W) -> T,
        noinline enterTransition: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = null,
        noinline exitTransition: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = null,
        crossinline content: @Composable AnimatedContentScope.(T) -> Unit
    ) where V : I {
        composable(direction, enterTransition, exitTransition) { backstackEntry ->
            val rootComponent = rootComponent(backstackEntry, navController)
            val subcomponent = rememberScoped(storeOwner = backstackEntry) {
                createSubcomponent(rootComponent)
            }

            LaunchedEffect(key1 = subcomponent) {
                logcat("Nav") { "Started listening to ${subcomponent.navigationManager}"}
            }

            ListenToNavigationManager(
                navigationManager = subcomponent.navigationManager,
                navigate = navigate,
                navController = navController
            )

            ListenToNavigationManager(
                navigationManager = CompositionLocals.current<NavigationManager>(),
                navigate = navigate,
                navController = navController
            )

            val viewModel = injectedViewModel(backstackEntry) {
                createVm(
                    backstackEntry.savedStateHandle,
                    backstackEntry.getInput(direction),
                    subcomponent
                )
            }

            content(viewModel)
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedContentScope.ListenToNavigationManager(
    navigationManager: NavigationManager,
    navigate: NavHostController.(ToDestinationCommand) -> Unit,
    navController: NavHostController,
) {
    val navCommand by navigationManager.commands.collectAsState(initial = null)
    LaunchedEffect(key1 = navCommand) {
        if (navCommand is BackCommand) {
            if (transition.targetState != EnterExitState.PostExit) {
                navController.popBackStack()
                navigationManager.confirm()
            }
        } else if (navCommand is ToDestinationCommand) {
            navCommand?.let { navController.navigate(it as ToDestinationCommand) }
            navigationManager.confirm()
        }
    }
}

interface NavigationCommandProvider<I : NavInput> {
    val featureRoute: String
    val arguments: List<NamedNavArgument>
    fun destination(input: I): ToDestinationCommand
}

fun NavigationCommandProvider<EmptyInput>.destination(): ToDestinationCommand = destination(EmptyInput)

fun NavGraphBuilder.addFeatureEntry(
    navController: NavHostController,
    featureEntry: FeatureEntry<*>,
    navigate: NavHostController.(ToDestinationCommand) -> Unit
) {
    with(featureEntry) {
        navigation(navController, navigate)
    }
}

fun NavGraphBuilder.composable(
    navigationCommandProvider: NavigationCommandProvider<*>,
    enterTransition: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = null,
    exitTransition: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)?,
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable(
        route = navigationCommandProvider.featureRoute,
        arguments = navigationCommandProvider.arguments,
        content = content,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
    )
}

inline fun <reified T> createNavType(isNullableAllowed: Boolean = true) =
    object : NavType<T>(isNullableAllowed) {
        override fun get(bundle: Bundle, key: String): T? =
            bundle.getString(key)?.deserialize()

        override fun parseValue(value: String): T = value.deserialize()

        override fun put(bundle: Bundle, key: String, value: T) =
            bundle.putString(key, value.serialize())
    }

inline fun <reified T : NavInput> createInputType(key: String, isNullableAllowed: Boolean = true) =
    object : InputType<T> {
        override val key: String = key
        override val type: NavType<T> = createNavType(isNullableAllowed)
    }

inline fun <reified T : NavOutput> createOutputType(key: String) =
    object : OutputType<T> {
        override val key: String = key
    }

interface NavDirection<T : NavInput> : NavigationCommandProvider<T>, InputType<T>

fun createNavDirection(route: String) =
    createNavDirection(route, EmptyInput)

inline fun <reified T : NavInput> createNavDirection(
    route: String,
    inputType: InputType<T>,
): NavDirection<T> = object : NavDirection<T>, InputType<T> by inputType {
    override val featureRoute: String = if (T::class == EmptyInput::class) {
        route
    } else {
        "$route/{${inputType.key}}"
    }
    override val arguments: List<NamedNavArgument> = if (T::class == EmptyInput::class) {
        emptyList()
    } else {
        listOf(
            navArgument(name = inputType.key) {
                type = inputType.type
                nullable = inputType.type.isNullableAllowed
            }
        )
    }

    override fun destination(input: T): ToDestinationCommand =
        object : ToDestinationCommand {
            override val args = arguments
            override val destinationFeatureRoute: String = featureRoute
            override val destination: String =
                if (input == EmptyInput) route else "$route/${input.serialize()}"
        }
}

inline fun <T, reified U> SavedStateHandle.getOutputFlow(type: T): Flow<U> where T : OutputType<U> {
    return getStateFlow<String?>(type.key, null)
        .mapNotNull {
            it?.deserialize<U>()
        }
}

inline fun <T, reified U> Bundle.getInputOrNull(type: T): U? where T : InputType<U> {
    return getString(type.key)?.deserialize()
}

inline fun <T, reified U> Bundle.getInput(type: T): U where T : InputType<U> {
    return getInputOrNull(type) ?: error("Input not found: ${type.key}")
}

inline fun <T, reified U> NavBackStackEntry.getInput(type: T): U where T : InputType<U> {
    return if (type.key.isNotEmpty()) {
        arguments?.getInput(type) ?: error("No arguments for type: $type")
    } else {
        EmptyInput as U
    }
}