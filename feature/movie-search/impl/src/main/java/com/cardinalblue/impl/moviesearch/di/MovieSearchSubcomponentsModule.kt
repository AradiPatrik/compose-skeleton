package com.cardinalblue.impl.moviesearch.di

import com.cardinalblue.impl.moviesearch.search.di.SearchSubcomponent
import dagger.Module

/**
 * Add subfeature-components here
 */
@Module(subcomponents = [SearchSubcomponent::class])
interface MovieSearchSubcomponentsModule