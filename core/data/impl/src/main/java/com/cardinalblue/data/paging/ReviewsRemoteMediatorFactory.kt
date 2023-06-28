package com.cardinalblue.data.paging

import dagger.assisted.AssistedFactory

@AssistedFactory
interface ReviewsRemoteMediatorFactory {
    fun create(movieId: Int): ReviewsRemoteMediator
}