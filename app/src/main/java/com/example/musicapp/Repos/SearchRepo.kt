package com.example.musicapp.Repos

import com.example.musicapp.Api.ApiManager
import com.example.musicapp.Api.WebService
import com.example.musicapp.Models.Artists
import com.example.musicapp.Models.Tracks
import io.reactivex.Observable
import org.koin.core.context.GlobalContext

class SearchRepo {
    companion object {
        var apiManager =  GlobalContext.get().koin.get<WebService>()
    }

    fun searchSongs(name : String): Observable<Tracks> {

        return apiManager.searchSongs(name)
    }

    fun searchArtists(name : String) : Observable<Artists>{
        return apiManager.searchArtists(name)
    }
}