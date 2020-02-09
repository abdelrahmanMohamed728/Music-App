package com.example.musicapp.Api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {
    companion object {
        var instance: WebService? = null
        private var BASE_URL = "https://api.deezer.com/"


        fun init() {
            instance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(WebService::class.java)
        }

    }
}