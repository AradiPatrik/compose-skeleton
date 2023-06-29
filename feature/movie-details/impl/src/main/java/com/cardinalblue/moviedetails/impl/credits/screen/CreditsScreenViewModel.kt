package com.cardinalblue.moviedetails.impl.credits.screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cardinalblue.domain.Credit
import com.cardinalblue.moviedetails.api.MovieDetailsInput
import com.cardinalblue.moviedetails.impl.credits.usecase.GetCredits
import com.cardinalblue.navigation.AssistedViewModelFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CreditsScreenViewModel @AssistedInject constructor(
    @Assisted val input: MovieDetailsInput,
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val getCredits: GetCredits,
) : ViewModel() {
    @AssistedFactory
    interface Factory : AssistedViewModelFactory<MovieDetailsInput, CreditsScreenViewModel>

    private val _credits = MutableStateFlow<List<Credit>>(emptyList())
    val credits = _credits.asStateFlow()

    init {
        viewModelScope.launch {
            _credits.value = getCredits(input.movieId)
        }
    }
}