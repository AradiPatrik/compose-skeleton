package com.cardinalblue.profile.impl.profiledetails.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cardinalblue.profile.impl.profiledetails.usecase.DoExample
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileDetailsScreenViewModel @Inject constructor(
    private val doExample: DoExample,
) : ViewModel() {
    fun onClick() = viewModelScope.launch {
        doExample("example")
    }
}