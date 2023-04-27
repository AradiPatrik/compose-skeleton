package com.cardinalblue.skeleton

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cardinalblue.featuredmovies.api.FeaturedMoviesFeatureEntry
import com.cardinalblue.moviesearch.api.MovieSearchFeatureEntry
import com.cardinalblue.navigation.NavigationManager
import com.cardinalblue.navigation.navigate
import com.cardinalblue.profile.api.ProfileFeatureEntry
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class AppViewModel @Inject constructor(
    private val navigationManager: NavigationManager
) : ViewModel() {
    enum class BottomSheetSelectedTab {
        MovieSearch, FeaturedMovies, Profile, None
    }

    val selectedTab = navigationManager.commands
        .map { navigationCommand ->
            when (navigationCommand.destinationFeatureRoute) {
                MovieSearchFeatureEntry.featureRoute -> BottomSheetSelectedTab.MovieSearch
                FeaturedMoviesFeatureEntry.featureRoute -> BottomSheetSelectedTab.FeaturedMovies
                ProfileFeatureEntry.featureRoute -> BottomSheetSelectedTab.Profile
                else -> BottomSheetSelectedTab.None
            }
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, BottomSheetSelectedTab.MovieSearch)

    fun navigateTo(selectedTab: BottomSheetSelectedTab) {
        when (selectedTab) {
            BottomSheetSelectedTab.MovieSearch -> navigationManager.navigate(MovieSearchFeatureEntry)
            BottomSheetSelectedTab.FeaturedMovies -> navigationManager.navigate(FeaturedMoviesFeatureEntry)
            BottomSheetSelectedTab.Profile -> navigationManager.navigate(ProfileFeatureEntry)
            BottomSheetSelectedTab.None -> Unit
        }
    }
}