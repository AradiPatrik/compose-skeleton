package com.cardinalblue.navigation

import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
annotation class FeatureEntryKey(val value: KClass<out FeatureEntry>)