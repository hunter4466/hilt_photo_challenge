package com.ravnnerdery.data.di

import com.ravnnerdery.domain.repository.MainRepository
import com.ravnnerdery.data.repository.MainRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<MainRepository> { MainRepositoryImpl(get(), get()) }
}