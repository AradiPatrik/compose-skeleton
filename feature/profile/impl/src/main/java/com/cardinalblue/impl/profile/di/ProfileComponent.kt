package com.cardinalblue.impl.profile.di

import com.cardinalblue.data.api.DataProvider
import com.cardinalblue.impl.profile.profile.screen.ProfileViewModel
import com.cardinalblue.platform.PlatformProvider
import com.cardinalblue.profile.api.ProfileProvider
import dagger.Component

@Component(
    dependencies = [PlatformProvider::class, DataProvider::class],
    modules = [ProfileModule::class]
)
interface ProfileComponent : ProfileProvider {
    val viewModel: ProfileViewModel
}