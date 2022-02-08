package com.ravnnerdery.data.repository

import android.util.Log
import com.ravnnerdery.data.database.DatabaseDao
import com.ravnnerdery.data.database.models.PhotoInfoEntity
import com.ravnnerdery.data.networking.models.PhotoInfoResponse
import com.ravnnerdery.domain.models.PhotoInfo
import com.ravnnerdery.domain.repository.MainRepository
import com.ravnnerdery.photo_challenge.network.PhotosApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepositoryImpl(
    private val photosDao: DatabaseDao,
    private val photosApi: PhotosApi,
) : MainRepository {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override suspend fun allPhotosFromDatabase(): MutableList<PhotoInfo> {
        val allPhotos: MutableList<PhotoInfo> = mutableListOf()
        photosDao.getPhotos().forEach {
            allPhotos.add(it.mapToDomainModel())
        }
        return allPhotos
    }

    override fun loadFromApiAndSetIntoDatabase() {
        photosApi.retrofitService.getPhotos().enqueue(object : Callback<List<PhotoInfoResponse>> {
            override fun onResponse(
                call: Call<List<PhotoInfoResponse>>,
                response: Response<List<PhotoInfoResponse>>
            ) {
                response.body()?.forEach { elm ->
                    addPostToDatabase(elm.id, elm.title, elm.url, elm.thumbnailUrl)
                }
            }

            override fun onFailure(call: Call<List<PhotoInfoResponse>>, t: Throwable) {
                Log.v("Network Response: ", t.message.toString())
            }
        })
    }

    private fun addPostToDatabase(id: Long, title: String, url: String, thumbUrl: String) {
        uiScope.launch(Dispatchers.IO) {
            val newPhoto = PhotoInfoEntity(id, title, url, thumbUrl)
            photosDao.insertPhoto(newPhoto)
        }
    }
}