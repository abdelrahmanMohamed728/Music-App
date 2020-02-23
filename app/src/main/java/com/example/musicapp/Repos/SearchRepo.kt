package com.example.musicapp.Repos

import com.example.musicapp.Api.WebService
import com.example.musicapp.Models.Tracks
import io.reactivex.Observable
import org.koin.core.context.GlobalContext

class SearchRepo {
    companion object {
        var apiManager =  GlobalContext.get().koin.get<WebService>()
    }

    fun search(name : String) : Observable<Tracks>{
        return apiManager.search(name)
    }
}