package com.ravnnerdery.photo_challenge.network

import com.ravnnerdery.data.networking.models.PhotoInfoResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


interface PhotosApiService {
    @GET("photos")
    fun getPhotos(): Call<List<PhotoInfoResponse>>
}

