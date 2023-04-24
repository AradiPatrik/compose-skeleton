package com.cardinalblue.data.di

import com.cardinalblue.data.network.MovieDbApi
import com.cardinalblue.data.network.MovieDbRequestAuthorizer
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.Date
import javax.inject.Singleton


@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
            .build()

    @Provides
    @Singleton
    fun provideMovieDbHttpClient(requestAuthorizer: MovieDbRequestAuthorizer): OkHttpClient =
        OkHttpClient.Builder()
            .addNetworkInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(requestAuthorizer)
            .build()

    @Provides
    @Singleton
    fun provideMovieDbApi(client: OkHttpClient, moshi: Moshi): MovieDbApi =
        Retrofit.Builder()
            .baseUrl(MovieDbApi.BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create()
}