package com.cardinalblue.impl.profile.di

import com.cardinalblue.impl.profile.profile.usecase.GetProfile
import com.cardinalblue.impl.profile.profile.usecase.GetProfileUseCase
import dagger.Binds
import dagger.Module

@Module
interface ProfileModule {
    @Binds
    fun bindGetProfile(impl: GetProfileUseCase): GetProfile
}