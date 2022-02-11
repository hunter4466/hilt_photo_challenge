package com.ravnnerdery.cleanphotochallenge.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ravnnerdery.data.useCases.GetAllPhotosUseCase
import com.ravnnerdery.data.useCases.LoadAllPhotosFromDBUseCase
import com.ravnnerdery.data.useCases.LoadApiToDbUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotoListViewModel @Inject constructor(
    private val loadApiToDbUseCase: LoadApiToDbUseCase,
    private val loadAllPhotosFromDBUseCase: LoadAllPhotosFromDBUseCase,
    getAllPhotosUseCase: GetAllPhotosUseCase,
) : ViewModel() {

    val allPhotosObservable = getAllPhotosUseCase

    init {
        refreshData()
        loadAllPhotosFromDBUseCase.execute()
    }

    private val _navigateToSnapshot = MutableLiveData<Long?>()
    var currentPosition: Int? = 0

    val navigateToSnapshot
        get() = _navigateToSnapshot

    fun onPhotoClicked(id: Long) {
        _navigateToSnapshot.value = id
    }

    fun onSnapshotNavigated() {
        _navigateToSnapshot.value = null
    }

    fun refreshData() {
            loadApiToDbUseCase.execute()
            loadAllPhotosFromDBUseCase.execute()
    }
}