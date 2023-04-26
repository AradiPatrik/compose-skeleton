package com.cardinalblue.impl.profile.profile.usecase

import com.cardinalblue.domain.Profile

fun interface GetProfile {
    suspend operator fun invoke(): Profile
}