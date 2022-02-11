package com.ravnnerdery.domain.repository

import com.ravnnerdery.domain.models.PhotoInfo
import io.reactivex.Observable

interface MainRepository {
    fun loadAllPhotosFromDatabase()
    fun loadFromApiAndSetIntoDatabase()
    fun provideAllPhotosObservable(): Observable<MutableList<PhotoInfo>>
}