package com.cardinalblue.impl.profile.profile.usecase

import com.cardinalblue.domain.Profile
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(): GetProfile {
    override suspend fun invoke(): Profile = Profile(
        firstName = "John",
        lastName = "Doe",
        email = "johndoe@gmail.com"
    )
}