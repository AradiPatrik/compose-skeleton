package com.cardinalblue.profile.impl.profiledetails.screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cardinalblue.navigation.AssistedViewModelFactory
import com.cardinalblue.navigation.EmptyInput
import com.cardinalblue.profile.impl.profiledetails.usecase.DoExample
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class ProfileDetailsScreenViewModel @AssistedInject constructor(
    private val doExample: DoExample,
    @Assisted private val input: EmptyInput,
    @Assisted private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    @AssistedFactory
    interface Factory : AssistedViewModelFactory<EmptyInput, ProfileDetailsScreenViewModel>

    @Suppress("unused")
    fun onClick() = viewModelScope.launch {
        doExample("example")
    }
}