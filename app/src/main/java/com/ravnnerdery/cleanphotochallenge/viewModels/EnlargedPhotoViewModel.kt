package com.ravnnerdery.cleanphotochallenge.viewModels

import androidx.lifecycle.ViewModel
import com.ravnnerdery.data.useCases.GetAllPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EnlargedPhotoViewModel @Inject constructor(getAllPhotosUseCase: GetAllPhotosUseCase) : ViewModel() {
    var currentPosition: Int? = 0
    val allPhotosObservable = getAllPhotosUseCase
}
