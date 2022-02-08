package com.ravnnerdery.data.useCases

import com.ravnnerdery.domain.repository.MainRepository
import com.ravnnerdery.domain.models.PhotoInfo

interface GetAllPhotosUseCase {
    suspend fun execute(): MutableList<PhotoInfo>
}

class GetAllPhotosUseCaseImpl(private val repo: MainRepository) : GetAllPhotosUseCase {
    override suspend fun execute(): MutableList<PhotoInfo> {
        return repo.allPhotosFromDatabase()
    }
}