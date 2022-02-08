package com.ravnnerdery.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ravnnerdery.data.database.models.PhotoInfoEntity

@Database(entities = [PhotoInfoEntity::class], version = 1, exportSchema = false)
abstract class PhotosDatabase : RoomDatabase() {
    abstract fun databaseDao(): DatabaseDao
}