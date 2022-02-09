//package com.ravnnerdery.cleanphotochallenge.di
//
//import androidx.lifecycle.ViewModel
//import androidx.recyclerview.widget.RecyclerView
//import com.ravnnerdery.cleanphotochallenge.viewModels.EnlargedPhotoViewModel
//import com.ravnnerdery.cleanphotochallenge.viewModels.PhotoListViewModel
//import com.ravnnerdery.data.useCases.GetAllPhotosUseCase
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//
////import org.koin.androidx.viewmodel.dsl.viewModel
////import org.koin.dsl.module
////
////val appModule = module {
////    viewModel { PhotoListViewModel(get()) }
////    viewModel { EnlargedPhotoViewModel(get()) }
////}
//@Module
//@InstallIn(SingletonComponent::class)
//object ViewModelsModule {
//    @Provides
//    fun provideEnlargedPhotoViewModel(getAllPhotosUseCase: GetAllPhotosUseCase): ViewModel{
//        return getAllPhotosUseCase.execute()
//    }
//}
//
//
