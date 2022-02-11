package com.ravnnerdery.data.repository

import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ravnnerdery.data.database.DatabaseDao
import com.ravnnerdery.data.database.models.PhotoInfoEntity
import com.ravnnerdery.data.networking.models.PhotoInfoResponse
import com.ravnnerdery.domain.models.PhotoInfo
import com.ravnnerdery.domain.repository.MainRepository
import com.ravnnerdery.photo_challenge.network.PhotosApiService
import dagger.Provides
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class MainRepositoryImpl @Inject constructor(
    private val photosDao: DatabaseDao,
    private val photosApi: PhotosApiService,
) : MainRepository {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private lateinit var allPhotosContainer: MutableList<PhotoInfo>
    val allPhotosObservable = BehaviorSubject.create<MutableList<PhotoInfo>>()

    override fun provideAllPhotosObservable(): Observable<MutableList<PhotoInfo>> {
        return allPhotosObservable
    }

    override fun loadAllPhotosFromDatabase(){
        uiScope.launch(Dispatchers.IO){
            val tempPhotosList: MutableList<PhotoInfo> = mutableListOf()
            photosDao.getPhotos().forEach {
                tempPhotosList.add(it.mapToDomainModel())
            }
            allPhotosContainer = tempPhotosList
            allPhotosObservable.onNext(allPhotosContainer)
        }
    }

    override fun loadFromApiAndSetIntoDatabase(){

        photosApi.getPhotos().enqueue(object : Callback<List<PhotoInfoResponse>> {

            override fun onResponse(
                call: Call<List<PhotoInfoResponse>>,
                response: Response<List<PhotoInfoResponse>>
            ) {
                response.body()?.forEach { elm ->
                    addPostToDatabase(elm.id, elm.title, elm.url, elm.thumbnailUrl)
                }
                loadAllPhotosFromDatabase()
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