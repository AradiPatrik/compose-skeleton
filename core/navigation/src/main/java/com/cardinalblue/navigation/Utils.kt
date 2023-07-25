package com.cardinalblue.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import java.util.UUID

fun <T> Flow<T>.stateInEager(
    scope: CoroutineScope,
): StateFlow<T?> = stateIn(scope, SharingStarted.Eagerly, null)

fun <T> Flow<T>.stateInLazy(
    scope: CoroutineScope
): StateFlow<T?> = stateIn(scope, SharingStarted.Lazily, null)

data class SnackbarMessage(val message: String, val uuid: UUID = UUID.randomUUID())

@Suppress("unused")
@Composable
fun ShowSnackbarMessage(
    message: SnackbarMessage,
    snackbarHostState: SnackbarHostState = CompositionLocals.current(),
) {
    LaunchedEffect(key1 = message) {
        snackbarHostState.showSnackbar(message.message, withDismissAction = true)
    }
}