package com.example.musicapp

import android.app.Application
import com.example.musicapp.Koin_DI.repoModule
import com.example.musicapp.Koin_DI.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MusicApplication :Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MusicApplication)
            modules(listOf(viewModelsModule, repoModule))
        }
    }
}