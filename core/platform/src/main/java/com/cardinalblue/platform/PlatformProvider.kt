package com.cardinalblue.platform

import android.content.Context
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class IoDispatcher

interface PlatformProvider {
    val context: Context

    @get:IoDispatcher
    val ioDispatcher: CoroutineDispatcher
}