package com.example.musicapp.Koin_DI

import com.example.musicapp.Api.WebService
import com.example.musicapp.Repos.ArtistRepo
import com.example.musicapp.Repos.ChartsRepo
import com.example.musicapp.Repos.FirebaseRepo
import com.example.musicapp.Repos.SearchRepo
import com.example.musicapp.Views.Artist.ArtistFragmentVM
import com.example.musicapp.Views.Home.HomeFragmentVM
import com.example.musicapp.Views.LogIn.LoginFragmentVM
import com.example.musicapp.Views.Search.SearchFragmentVM
import com.example.musicapp.Views.Song.SongFragmentVM
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val viewModelsModule = module {
    single { ArtistFragmentVM(get()) }
    single { LoginFragmentVM(get()) }
    single { SongFragmentVM() }
    single { HomeFragmentVM(get()) }
    single { SearchFragmentVM(get()) }
}


val repoModule = module {
    single {
        ArtistRepo()
    }

    single {
        ChartsRepo()
    }

    single {
        SearchRepo()
    }

    single(named("BASE_URL")) {
        "https://api.deezer.com"
    }

    single {
        FirebaseRepo()
    }

    single {
        Retrofit.Builder()
            .baseUrl(get<String>(named("BASE_URL")))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(WebService::class.java)
    }
}
