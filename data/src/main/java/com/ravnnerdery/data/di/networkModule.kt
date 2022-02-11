package com.ravnnerdery.data.di

import android.util.Log
import com.ravnnerdery.photo_challenge.network.PhotosApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetWorkModule {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Singleton
    @Provides
    fun getRetrofit(): Retrofit {
        Log.v("TRACE CHASE","<<<<<<<<<<<< INITIALIZED RETROFIT SERVICE >>>>>>>>>>>>>>>>>>>")
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun getPhotosAPI(retrofit: Retrofit): PhotosApiService {
        Log.v("TRACE CHASE","<<<<<<<<<<<< INITIALIZED API >>>>>>>>>>>>>>>>>>>")
        val retrofitService: PhotosApiService by lazy {
            retrofit.create(PhotosApiService::class.java)
        }
        return retrofitService
    }

}