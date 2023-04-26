package com.cardinalblue.impl.profile.profile.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cardinalblue.impl.profile.profile.usecase.GetProfile
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val getProfile: GetProfile
) : ViewModel() {
    val profile = flow {
        emit(getProfile())
    }.stateIn(viewModelScope, SharingStarted.Lazily, null)
}
