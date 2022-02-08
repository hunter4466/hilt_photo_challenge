package com.ravnnerdery.cleanphotochallenge.di

import com.ravnnerdery.cleanphotochallenge.viewModels.EnlargedPhotoViewModel
import com.ravnnerdery.cleanphotochallenge.viewModels.PhotoListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { PhotoListViewModel(get()) }
    viewModel { EnlargedPhotoViewModel(get()) }
}
