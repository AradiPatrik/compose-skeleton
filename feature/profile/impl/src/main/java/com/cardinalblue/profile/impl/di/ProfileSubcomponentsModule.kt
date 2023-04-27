package com.cardinalblue.profile.impl.di

import com.cardinalblue.profile.impl.profiledetails.di.ProfileDetailsSubcomponent
import dagger.Module

/**
 * Add subfeature-components here
 */
@Module(subcomponents = [ProfileDetailsSubcomponent::class])
interface ProfileSubcomponentsModule