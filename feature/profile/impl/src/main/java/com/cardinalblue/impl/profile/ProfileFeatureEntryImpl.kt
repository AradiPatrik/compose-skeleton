package com.cardinalblue.impl.profile

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.cardinalblue.impl.profile.di.ProfileComponent
import com.cardinalblue.impl.profile.profile.screen.ProfileScreen
import com.cardinalblue.navigation.RootComponentHolder
import com.cardinalblue.navigation.injectedViewModel
import com.cardinalblue.profile.api.ProfileFeatureEntry
import javax.inject.Inject

class ProfileFeatureEntryImpl @Inject constructor() : ProfileFeatureEntry(), RootComponentHolder<ProfileComponent> {
    override val rootRoute: String = "@profile"

    override fun NavGraphBuilder.navigation(navController: NavHostController) {
        navigation(featureRoute, rootRoute) {
            composable(featureRoute, arguments) { backstackEntry ->
                val rootComponent = rootComponent(
                    currentEntry = backstackEntry,
                    navController = navController,
                )

                val viewModel = injectedViewModel(backstackEntry) {
                    rootComponent.viewModel
                }

                ProfileScreen(viewModel = viewModel)
            }
        }
    }

    @Composable
    override fun provideRootComponent(
        rootEntry: NavBackStackEntry,
        navController: NavController,
        arguments: Bundle?
    ): ProfileComponent {
        TODO("Not yet implemented")
    }
}