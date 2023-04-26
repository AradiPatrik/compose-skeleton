package com.cardinalblue.impl.profile.di

import com.cardinalblue.impl.profile.ProfileFeatureEntryImpl
import com.cardinalblue.navigation.FeatureEntry
import com.cardinalblue.navigation.FeatureEntryKey
import com.cardinalblue.profile.api.ProfileFeatureEntry
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
interface ProfileEntryFeatureModule {
    @Binds
    @IntoMap
    @FeatureEntryKey(ProfileFeatureEntry::class)
    @Singleton
    fun bindProfileFeatureEntry(impl: ProfileFeatureEntryImpl): FeatureEntry<*>
}