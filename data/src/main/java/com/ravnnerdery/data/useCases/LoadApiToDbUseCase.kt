package com.ravnnerdery.data.useCases

import com.ravnnerdery.domain.repository.MainRepository
import javax.inject.Inject

interface LoadApiToDbUseCase {
    fun execute()
}

class LoadApiToDbUseCaseImpl @Inject constructor(private val repo: MainRepository) : LoadApiToDbUseCase {
    override fun execute() {
        repo.loadFromApiAndSetIntoDatabase()
    }
}
