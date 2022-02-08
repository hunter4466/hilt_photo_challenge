package com.ravnnerdery.data.di

import androidx.room.Room
import com.ravnnerdery.data.database.PhotosDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val PHOTOS_DATABASE = "Photos_database"

val dataBaseModule = module {
    factory { (get() as PhotosDatabase).databaseDao() }
    factory {
        Room.databaseBuilder(
            androidContext().applicationContext,
            PhotosDatabase::class.java,
            PHOTOS_DATABASE
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}