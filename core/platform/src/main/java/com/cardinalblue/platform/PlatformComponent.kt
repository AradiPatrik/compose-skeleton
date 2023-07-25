package com.cardinalblue.platform

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [PlatformModule::class])
interface PlatformComponent : PlatformProvider {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance
            @ApplicationContext
            context: Context
        ): PlatformComponent
    }
}