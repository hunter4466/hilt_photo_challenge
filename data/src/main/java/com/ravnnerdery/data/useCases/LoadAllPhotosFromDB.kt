package com.ravnnerdery.data.useCases

import com.ravnnerdery.domain.repository.MainRepository
import javax.inject.Inject


interface LoadAllPhotosFromDBUseCase {
    fun execute()
}

class LoadAllPhotosFromDBUseCaseImpl@Inject constructor(val repo: MainRepository): LoadAllPhotosFromDBUseCase {
    override fun execute() {
        repo.loadAllPhotosFromDatabase()
    }
}