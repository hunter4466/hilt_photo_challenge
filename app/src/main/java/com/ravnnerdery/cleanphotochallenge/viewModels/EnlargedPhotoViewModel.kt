package com.ravnnerdery.cleanphotochallenge.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ravnnerdery.data.useCases.GetAllPhotosUseCase
import com.ravnnerdery.domain.models.PhotoInfo
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnlargedPhotoViewModel @Inject constructor(private val getAllPhotos: GetAllPhotosUseCase) : ViewModel() {

    var currentPosition: Int? = 0
    val mutableAllPhotos = MutableLiveData<List<PhotoInfo>>(emptyList())
    fun allPhotos() {
        viewModelScope.launch(Dispatchers.IO) {
            mutableAllPhotos.postValue(getAllPhotos.execute())
        }
    }
}
