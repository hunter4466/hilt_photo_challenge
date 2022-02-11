package com.ravnnerdery.photo_challenge.network

import com.ravnnerdery.data.networking.models.PhotoInfoResponse
import retrofit2.Call
import retrofit2.http.GET


interface PhotosApiService {
    @GET("photos")
    fun getPhotos(): Call<List<PhotoInfoResponse>>
}

