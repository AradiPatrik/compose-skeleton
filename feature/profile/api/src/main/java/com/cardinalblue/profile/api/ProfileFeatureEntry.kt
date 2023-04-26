package com.cardinalblue.profile.api

import androidx.navigation.NamedNavArgument
import com.cardinalblue.navigation.EmptyInput
import com.cardinalblue.navigation.FeatureEntry
import com.cardinalblue.navigation.NavigationCommand

abstract class ProfileFeatureEntry : FeatureEntry<EmptyInput> {
    override val featureRoute: String = "profile"

    override val arguments: List<NamedNavArgument> = emptyList()

    override fun destination(input: EmptyInput): NavigationCommand = object : NavigationCommand {
        override val arguments: List<NamedNavArgument> = this@ProfileFeatureEntry.arguments
        override val destination: String = featureRoute
    }
}