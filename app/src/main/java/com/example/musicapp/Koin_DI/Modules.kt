package com.example.musicapp.Koin_DI

import com.example.musicapp.Api.ApiManager
import com.example.musicapp.Views.Artist.ArtistFragmentVM
import com.example.musicapp.Views.Home.HomeFragmentVM
import com.example.musicapp.Views.LogIn.LoginFragmentVM
import com.example.musicapp.Views.Song.SongFragmentVM
import org.koin.dsl.module

val viewModelsModule = module {
    single { ArtistFragmentVM() }
    single { LoginFragmentVM() }
    single { SongFragmentVM() }
    single { HomeFragmentVM() }
}

val repoModule = module {
}