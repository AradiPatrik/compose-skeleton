package com.cardinalblue.moviedetails.impl.common.reviews.di

import com.cardinalblue.moviedetails.impl.common.reviews.usecase.GetReviews
import com.cardinalblue.moviedetails.impl.common.reviews.usecase.GetReviewsUseCase
import com.cardinalblue.navigation.SubfeatureScoped
import dagger.Binds
import dagger.Module

@Module
interface ReviewsModule {
    @Binds
    @SubfeatureScoped
    fun bindGetReviews(impl: GetReviewsUseCase): GetReviews
}
