package com.ravnnerdery.photo_challenge.network

import com.ravnnerdery.data.networking.models.PhotoInfoResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface PostsApiService {
    @GET("photos")
    fun getPhotos(): Call<List<PhotoInfoResponse>>
}

object PhotosApi {
    val retrofitService: PostsApiService by lazy {
        retrofit.create(PostsApiService::class.java)
    }
}
