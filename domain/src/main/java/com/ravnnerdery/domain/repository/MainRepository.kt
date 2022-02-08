package com.ravnnerdery.domain.repository

import com.ravnnerdery.domain.models.PhotoInfo

interface MainRepository {
    suspend fun allPhotosFromDatabase(): MutableList<PhotoInfo>
    fun loadFromApiAndSetIntoDatabase()
}