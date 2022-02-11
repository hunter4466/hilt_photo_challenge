package com.ravnnerdery.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ravnnerdery.data.database.models.PhotoInfoEntity

@Dao
interface DatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhoto(photoEntity: PhotoInfoEntity)

    @Query("SELECT * from photo_table LIMIT 100")
    fun getPhotos(): List<PhotoInfoEntity>

}
