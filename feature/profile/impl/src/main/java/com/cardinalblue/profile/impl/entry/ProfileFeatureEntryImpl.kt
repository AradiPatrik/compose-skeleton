package com.cardinalblue.profile.impl.entry

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.cardinalblue.data.api.DataProvider
import com.cardinalblue.profile.api.ProfileFeatureEntry
import com.cardinalblue.profile.impl.di.DaggerProfileRootComponent
import com.cardinalblue.profile.impl.di.ProfileRootComponent
import com.cardinalblue.profile.impl.profiledetails.screen.ProfileDetailsScreen
import com.cardinalblue.navigation.CompositionLocals
import com.cardinalblue.navigation.NavigationProvider
import com.cardinalblue.navigation.RootComponentHolder
import com.cardinalblue.navigation.injectedViewModel
import com.cardinalblue.navigation.rememberScoped
import com.cardinalblue.platform.PlatformProvider
import javax.inject.Inject

/**
 * The entry point for the feature. Provides root component and navigation graph for the feature.
 */
class ProfileFeatureEntryImpl @Inject constructor() : ProfileFeatureEntry(),
    RootComponentHolder<ProfileRootComponent> {
    override val rootRoute: String
        get() = "@profile"

    override fun NavGraphBuilder.navigation(
        navController: NavHostController,
    ) {
        navigation(startDestination = featureRoute, route = rootRoute) {
            composable(featureRoute, arguments) { backstackEntry ->
                val rootComponent = rootComponent(backstackEntry, navController)

                val viewModel = injectedViewModel(backstackEntry) {
                    rootComponent.profileDetailsSubcomponentFactory.create().viewModel
                }

                ProfileDetailsScreen(viewModel = viewModel)
            }
        }
    }

    @Composable
    override fun provideRootComponent(
        rootEntry: NavBackStackEntry,
        navController: NavController,
        arguments: Bundle?
    ): ProfileRootComponent {
        val currentDataProvider = CompositionLocals.current<DataProvider>()
        val currentPlatformProvider = CompositionLocals.current<PlatformProvider>()
        val currentNavigationProvider = CompositionLocals.current<NavigationProvider>()
        return rememberScoped(rootEntry) {
            DaggerProfileRootComponent.builder()
                .dataProvider(currentDataProvider)
                .platformProvider(currentPlatformProvider)
                .navigationProvider(currentNavigationProvider)
                .build()
        }
    }
}