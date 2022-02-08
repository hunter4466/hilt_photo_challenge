package com.ravnnerdery.data.useCases

import com.ravnnerdery.domain.repository.MainRepository

interface LoadApiToDbUseCase {
    fun execute()
}

class LoadApiToDbUseCaseImpl(private val repo: MainRepository) : LoadApiToDbUseCase {
    override fun execute() {
        repo.loadFromApiAndSetIntoDatabase()
    }
}
