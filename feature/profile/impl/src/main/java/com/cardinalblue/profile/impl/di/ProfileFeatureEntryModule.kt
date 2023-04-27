package com.cardinalblue.profile.impl.di

import com.cardinalblue.profile.api.ProfileFeatureEntry
import com.cardinalblue.profile.impl.entry.ProfileFeatureEntryImpl
import com.cardinalblue.navigation.FeatureEntry
import com.cardinalblue.navigation.FeatureEntryKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

/**
 * Binding the navigation entry points into this feature
 */
@Module
interface ProfileFeatureEntryModule {
    @Binds
    @IntoMap
    @FeatureEntryKey(ProfileFeatureEntry::class)
    @Singleton
    fun bindProfileEntry(impl: ProfileFeatureEntryImpl): FeatureEntry<*>
}