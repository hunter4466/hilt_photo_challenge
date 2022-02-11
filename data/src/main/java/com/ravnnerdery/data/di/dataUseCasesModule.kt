package com.ravnnerdery.data.di

import com.ravnnerdery.data.useCases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataUseCasesModule {
    @Provides
    @Singleton
    fun bindLoadApiToDbUseCase(
        getAllPhotosUseCaseImpl: LoadApiToDbUseCaseImpl
    ): LoadApiToDbUseCase {
        return getAllPhotosUseCaseImpl
    }

    @Provides
    @Singleton
    fun bindGetAllPhotosUseCase(
        getAllPhotosUseCaseImpl: GetAllPhotosUseCaseImpl
    ): GetAllPhotosUseCase {
        return getAllPhotosUseCaseImpl
    }

    @Provides
    @Singleton
    fun bindLoadAllPhotosFromDBUseCase(
    loadAllPhotosFromDBUseCaseImpl: LoadAllPhotosFromDBUseCaseImpl
    ): LoadAllPhotosFromDBUseCase {
        return loadAllPhotosFromDBUseCaseImpl
    }
}