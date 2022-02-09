package com.ravnnerdery.data.di

import com.ravnnerdery.data.useCases.GetAllPhotosUseCase
import com.ravnnerdery.data.useCases.GetAllPhotosUseCaseImpl
import com.ravnnerdery.data.useCases.LoadApiToDbUseCase
import com.ravnnerdery.data.useCases.LoadApiToDbUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataUseCasesModule {
    @Provides
    fun bindLoadApiToDbUseCase(
        getAllPhotosUseCaseImpl: LoadApiToDbUseCaseImpl
    ): LoadApiToDbUseCase {
        return getAllPhotosUseCaseImpl
    }

    @Provides
    fun bindGetAllPhotosUseCase(
        getAllPhotosUseCaseImpl: GetAllPhotosUseCaseImpl
    ): GetAllPhotosUseCase {
        return getAllPhotosUseCaseImpl
    }
}