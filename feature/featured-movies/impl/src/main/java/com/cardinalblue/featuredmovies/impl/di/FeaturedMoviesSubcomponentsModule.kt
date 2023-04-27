package com.cardinalblue.featuredmovies.impl.di

import com.cardinalblue.featuredmovies.impl.featuredmovieslist.di.FeaturedMoviesListSubcomponent
import dagger.Module

/**
 * Add subfeature-components here
 */
@Module(subcomponents = [FeaturedMoviesListSubcomponent::class])
interface FeaturedMoviesSubcomponentsModule