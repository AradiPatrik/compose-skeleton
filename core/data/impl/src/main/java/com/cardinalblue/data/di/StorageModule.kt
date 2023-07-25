package com.cardinalblue.data.di

import android.content.Context
import com.cardinalblue.data.storage.db.AppDatabase
import com.cardinalblue.platform.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase = AppDatabase.getInstance(context)
}