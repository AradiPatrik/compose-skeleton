package com.cardinalblue.profile.impl.profiledetails.di

import com.cardinalblue.profile.impl.profiledetails.usecase.DoExample
import com.cardinalblue.profile.impl.profiledetails.usecase.DoExampleUseCase
import com.cardinalblue.navigation.SubfeatureScoped
import dagger.Binds
import dagger.Module

/**
 * Add subfeature-wide available bindings here
 */
@Module
interface ProfileDetailsModule {
    @Binds
    @SubfeatureScoped
    fun bindDoExampleUseCase(impl: DoExampleUseCase): DoExample
}