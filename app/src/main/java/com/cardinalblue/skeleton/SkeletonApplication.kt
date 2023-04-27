package com.cardinalblue.skeleton

import android.app.Application
import com.cardinalblue.data.di.DaggerDataComponent
import com.cardinalblue.platform.DaggerPlatformComponent
import com.cardinalblue.skeleton.di.AppProvider
import com.cardinalblue.skeleton.di.DaggerAppComponent
import logcat.AndroidLogcatLogger
import logcat.LogPriority
import logcat.LogPriority.VERBOSE

class SkeletonApplication : Application() {
    lateinit var appProvider: AppProvider
        private set

    override fun onCreate() {
        super.onCreate()

        val platformProvider = DaggerPlatformComponent.factory().create(this)
        appProvider = DaggerAppComponent.builder()
            .platformProvider(platformProvider)
            .dataProvider(DaggerDataComponent.builder().platformProvider(platformProvider).build())
            .build()

        AndroidLogcatLogger.installOnDebuggableApp(this, minPriority = VERBOSE)
    }
}

val Application.appProvider: AppProvider
    get() = (this as SkeletonApplication).appProvider