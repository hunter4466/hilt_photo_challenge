package com.ravnnerdery.data.useCases

import com.ravnnerdery.domain.repository.MainRepository
import com.ravnnerdery.domain.models.PhotoInfo
import io.reactivex.Observable
import javax.inject.Inject

interface GetAllPhotosUseCase {
    fun execute(): Observable<MutableList<PhotoInfo>>
}

class GetAllPhotosUseCaseImpl @Inject constructor(val repo: MainRepository) : GetAllPhotosUseCase {
    override fun execute(): Observable<MutableList<PhotoInfo>> {
       return repo.provideAllPhotosObservable()
    }
}