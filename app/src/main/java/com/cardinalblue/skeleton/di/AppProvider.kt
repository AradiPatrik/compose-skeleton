package com.cardinalblue.skeleton.di

import com.cardinalblue.api.DataProvider
import com.cardinalblue.navigation.FeatureEntriesProvider
import com.cardinalblue.platform.PlatformProvider

interface AppProvider : PlatformProvider, DataProvider, FeatureEntriesProvider
