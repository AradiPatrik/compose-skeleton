package com.cardinalblue.skeleton.di

import com.cardinalblue.data.api.DataProvider
import com.cardinalblue.navigation.FeatureEntriesProvider
import com.cardinalblue.navigation.NavigationProvider
import com.cardinalblue.platform.PlatformProvider

interface AppProvider : PlatformProvider, DataProvider, FeatureEntriesProvider, NavigationProvider
