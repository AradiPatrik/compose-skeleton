package com.cardinalblue.moviedetails.impl.credits.di

import com.cardinalblue.moviedetails.impl.credits.usecase.GetCredits
import com.cardinalblue.moviedetails.impl.credits.usecase.GetCreditsUseCase
import com.cardinalblue.navigation.SubfeatureScoped
import dagger.Binds
import dagger.Module

@Module
interface CreditsModule {
    @Binds
    @SubfeatureScoped
    fun bindGetCreditsUseCase(impl: GetCreditsUseCase): GetCredits
}