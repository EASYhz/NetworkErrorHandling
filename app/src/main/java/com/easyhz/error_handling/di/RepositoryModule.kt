package com.easyhz.error_handling.di

import com.easyhz.error_handling.data.repository.MainRepositoryImpl
import com.easyhz.error_handling.domain.repository.MainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    @ViewModelScoped
    abstract fun bindsCoffeeRepository(
        mainRepositoryImpl: MainRepositoryImpl
    ): MainRepository
}