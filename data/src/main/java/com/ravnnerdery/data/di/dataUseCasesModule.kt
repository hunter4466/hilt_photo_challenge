package com.ravnnerdery.data.di

import com.ravnnerdery.data.useCases.GetAllPhotosUseCase
import com.ravnnerdery.data.useCases.GetAllPhotosUseCaseImpl
import com.ravnnerdery.data.useCases.LoadApiToDbUseCase
import com.ravnnerdery.data.useCases.LoadApiToDbUseCaseImpl
import org.koin.dsl.module

val dataUseCasesModule = module {
    factory<GetAllPhotosUseCase> { GetAllPhotosUseCaseImpl(get()) }
    factory<LoadApiToDbUseCase> { LoadApiToDbUseCaseImpl(get()) }
}