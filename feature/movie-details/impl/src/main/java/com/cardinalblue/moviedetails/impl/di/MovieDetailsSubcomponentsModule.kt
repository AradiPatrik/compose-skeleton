package com.cardinalblue.moviedetails.impl.di

import com.cardinalblue.moviedetails.impl.credits.di.CreditsSubcomponent
import com.cardinalblue.moviedetails.impl.movie.di.MovieSubcomponent
import dagger.Module

/**
 * Add subfeature-components here
 */
@Module(subcomponents = [MovieSubcomponent::class, CreditsSubcomponent::class])
interface MovieDetailsSubcomponentsModule