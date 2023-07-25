package com.cardinalblue.navigation

import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class FeatureScoped

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class SubfeatureScoped

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class CompositionScoped