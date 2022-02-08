package com.ravnnerdery.data.di

import com.ravnnerdery.photo_challenge.network.PhotosApi
import org.koin.dsl.module

val networkModule = module {
    factory { PhotosApi }
}