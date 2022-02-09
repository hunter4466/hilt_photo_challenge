package com.ravnnerdery.data.di
import android.content.Context
import androidx.room.Room
import com.ravnnerdery.data.database.DatabaseDao
import com.ravnnerdery.data.database.PhotosDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val PHOTOS_DATABASE = "Photos_database"

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideDatabaseDao (photosDatabase: PhotosDatabase): DatabaseDao {
        return photosDatabase.databaseDao()
    }
    @Provides
    @Singleton
    fun providePhotosDatabase (@ApplicationContext appContext: Context) : PhotosDatabase {
        return Room.databaseBuilder(
            appContext,
            PhotosDatabase::class.java,
            PHOTOS_DATABASE
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}