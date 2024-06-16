package com.easyhz.error_handling.di

import com.easyhz.error_handling.network.api.MainService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideExampleService(
        @MainRetrofit retrofit: Retrofit
    ): MainService = retrofit.create(MainService::class.java)
}