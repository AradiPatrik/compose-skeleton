package com.cardinalblue.profile.impl.entry

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.cardinalblue.data.api.DataProvider
import com.cardinalblue.navigation.BaseFeatureEntry
import com.cardinalblue.navigation.CompositionLocals
import com.cardinalblue.navigation.EmptyInput
import com.cardinalblue.navigation.FeatureGraphBuilderScope
import com.cardinalblue.navigation.rememberScoped
import com.cardinalblue.platform.PlatformProvider
import com.cardinalblue.profile.api.ProfileFeatureEntry
import com.cardinalblue.profile.impl.di.DaggerProfileRootComponent
import com.cardinalblue.profile.impl.di.ProfileRootComponent
import com.cardinalblue.profile.impl.profiledetails.di.ProfileDetailsSubcomponent
import com.cardinalblue.profile.impl.profiledetails.screen.ProfileDetailsScreen
import javax.inject.Inject

/**
 * The entry point for the feature. Provides root component and navigation graph for the feature.
 */
class ProfileFeatureEntryImpl @Inject constructor() :
    BaseFeatureEntry<EmptyInput, ProfileRootComponent>(
        rootRoute = "@profile",
        ProfileFeatureEntry.featureRoute
    ), ProfileFeatureEntry {
    override fun FeatureGraphBuilderScope.buildNavigation() {
        addNode(
            direction = ProfileFeatureEntry.Direction,
            subcomponentFactory = ProfileRootComponent::profileDetailsSubcomponentFactory,
            viewModelFactory = ProfileDetailsSubcomponent::viewModelFactory,
            content = { ProfileDetailsScreen(it) }
        )
    }

    @Composable
    override fun provideRootComponent(
        rootEntry: NavBackStackEntry,
        navController: NavController,
        arguments: Bundle?
    ): ProfileRootComponent {
        val currentDataProvider = CompositionLocals.current<DataProvider>()
        val currentPlatformProvider = CompositionLocals.current<PlatformProvider>()
        return rememberScoped(rootEntry) {
            DaggerProfileRootComponent.factory()
                .create(
                    dataProvider = currentDataProvider,
                    platformProvider = currentPlatformProvider,
                    navController = navController
                )
        }
    }
}