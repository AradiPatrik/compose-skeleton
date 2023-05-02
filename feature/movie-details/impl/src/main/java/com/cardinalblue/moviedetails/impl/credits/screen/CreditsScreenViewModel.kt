package com.cardinalblue.moviedetails.impl.credits.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cardinalblue.domain.Credit
import com.cardinalblue.moviedetails.impl.credits.usecase.GetCredits
import com.cardinalblue.moviedetails.impl.di.MovieId
import com.cardinalblue.navigation.SubfeatureScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@SubfeatureScoped
class CreditsScreenViewModel @Inject constructor(
    @MovieId val movieId: Int,
    private val getCredits: GetCredits
) : ViewModel() {
    private val credits = MutableStateFlow<List<Credit>>(emptyList())

    init {
        viewModelScope.launch {
            credits.value = getCredits(movieId)
        }
    }
}