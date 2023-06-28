package com.cardinalblue.data.paging

import dagger.assisted.AssistedFactory

@AssistedFactory
interface MoviesRemoteMediatorFactory {
    fun create(query: String): MoviesRemoteMediator
}