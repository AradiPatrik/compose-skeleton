package com.cardinalblue.profile.impl.di

import androidx.navigation.NavController
import com.cardinalblue.data.api.DataProvider
import com.cardinalblue.profile.api.ProfileProvider
import com.cardinalblue.profile.impl.profiledetails.di.ProfileDetailsSubcomponent
import com.cardinalblue.navigation.FeatureScoped
import com.cardinalblue.navigation.NavigationProvider
import com.cardinalblue.platform.PlatformProvider
import dagger.BindsInstance
import dagger.Component

/**
 * The root component for the feature. It is providing the feature-wide dependencies. Exposes them
 * to other features via [ProfileProvider].
 */
@FeatureScoped
@Component(
    dependencies = [
        DataProvider::class,
        PlatformProvider::class,
    ],
    modules = [
        ProfileRootModule::class,
        ProfileSubcomponentsModule::class
    ]
)
interface ProfileRootComponent : ProfileProvider {
    val profileDetailsSubcomponentFactory: ProfileDetailsSubcomponent.Factory

    @Component.Factory
    interface Factory {
        fun create(
            dataProvider: DataProvider,
            platformProvider: PlatformProvider,
            @BindsInstance navController: NavController,
        ): ProfileRootComponent
    }
}
