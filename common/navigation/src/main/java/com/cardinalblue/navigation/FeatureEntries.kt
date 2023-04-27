package com.cardinalblue.navigation

typealias FeatureEntries = Map<Class<out FeatureEntry<*>>, @JvmSuppressWildcards FeatureEntry<*>>

fun <T : FeatureEntry<*>> FeatureEntries.find(clazz: Class<T>) =
    findOrNull(clazz) ?: error("Unable to find '${clazz}' destination.")

@Suppress("unchecked")
fun <T: FeatureEntry<*>> FeatureEntries.findOrNull(clazz: Class<T>) = this[clazz] as? T

inline fun <reified T : FeatureEntry<*>> FeatureEntries.find(): T = find(T::class.java) as T
