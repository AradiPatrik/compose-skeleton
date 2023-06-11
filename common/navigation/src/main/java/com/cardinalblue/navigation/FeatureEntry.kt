package com.cardinalblue.navigation

import android.os.Bundle
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.cardinalblue.platform.deserialize
import com.cardinalblue.platform.serialize
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull

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
    fun NavGraphBuilder.navigation(navController: NavHostController)
}

interface NavigationCommandProvider<I : NavInput> {
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

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.composable(
    navigationCommandProvider: NavigationCommandProvider<*>,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navigationCommandProvider.featureRoute,
        arguments = navigationCommandProvider.arguments,
        content = content
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

inline fun <reified T : NavInput> createNavigationCommandProvider(
    route: String,
    inputType: InputType<*> = EmptyInput,
) = object : NavigationCommandProvider<T> {
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

    override fun destination(input: T): NavigationCommand =
        object : NavigationCommand {
            override val args = arguments
            override val destinationFeatureRoute: String = featureRoute
            override val destination: String =
                if (input == EmptyInput) route else "$route/${input.serialize()}"
        }
}

inline fun <T, reified U> SavedStateHandle.getOutputFlow(type: T): Flow<U> where T : OutputType<U> {
    return getStateFlow<String?>(type.key, null)
        .mapNotNull {
            delay(500) // hack: wait for ui to be shown
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
    return arguments?.getInput(type) ?: error("No arguments for ${type.key}")
}