package com.ravnnerdery.cleanphotochallenge.application

import android.app.Application
import com.ravnnerdery.cleanphotochallenge.di.appModule
import com.ravnnerdery.data.di.dataBaseModule
import com.ravnnerdery.data.di.dataUseCasesModule
import com.ravnnerdery.data.di.networkModule
import com.ravnnerdery.data.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

import org.koin.core.logger.Level

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MainApplication)
            modules(appModules + dataModules)
        }
    }
    val appModules = listOf(appModule, dataBaseModule)
    val dataModules = listOf(dataBaseModule, networkModule, repositoryModule, dataUseCasesModule)
}

