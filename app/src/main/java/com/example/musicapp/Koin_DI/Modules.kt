package com.example.musicapp.Koin_DI

import com.example.musicapp.Api.WebService
import com.example.musicapp.Repos.ArtistRepo
import com.example.musicapp.Repos.ChartsRepo
import com.example.musicapp.Repos.FirebaseRepo
import com.example.musicapp.Repos.SearchRepo
import com.example.musicapp.views.Artist.ArtistFragmentVM
import com.example.musicapp.views.Home.HomeFragmentVM
import com.example.musicapp.views.authentication.login.LoginFragmentVM
import com.example.musicapp.views.Search.SearchFragmentVM
import com.example.musicapp.views.Song.SongFragmentVM
import com.example.musicapp.views.authentication.signup.SignUpFragmentVM
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val viewModelsModule = module {
    factory { ArtistFragmentVM(get()) }
    factory { LoginFragmentVM(get()) }
    factory { SongFragmentVM() }
    factory { HomeFragmentVM(get()) }
    factory { SearchFragmentVM(get()) }
    factory { SignUpFragmentVM(get()) }
}


val repoModule = module {
    factory {
        ArtistRepo()
    }

    factory {
        ChartsRepo()
    }

    factory {
        SearchRepo()
    }

    factory(named("BASE_URL")) {
        "https://api.deezer.com"
    }

    factory {
        FirebaseRepo()
    }

    factory {
        Retrofit.Builder()
            .baseUrl(get<String>(named("BASE_URL")))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(WebService::class.java)
    }
}
