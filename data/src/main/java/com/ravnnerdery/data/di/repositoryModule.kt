package com.ravnnerdery.data.di

import android.content.Context
import androidx.room.Room
import com.ravnnerdery.data.database.DatabaseDao
import com.ravnnerdery.data.database.PhotosDatabase
import com.ravnnerdery.data.repository.MainRepositoryImpl
import com.ravnnerdery.domain.repository.MainRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//import com.ravnnerdery.domain.repository.MainRepository
//import com.ravnnerdery.data.repository.MainRepositoryImpl
//import org.koin.dsl.module
//
//val repositoryModule = module {
//    single<MainRepository> { MainRepositoryImpl(get(), get()) }
//}
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideMainRepositoryImpl (repository: MainRepositoryImpl): MainRepository {
        return repository
    }
}