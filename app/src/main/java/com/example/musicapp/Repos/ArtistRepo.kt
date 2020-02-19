package com.example.musicapp.Repos

import com.example.musicapp.Api.ApiManager
import com.example.musicapp.Api.WebService
import com.example.musicapp.Models.Artist
import com.example.musicapp.Models.Tracks
import io.reactivex.Observable
import org.koin.core.context.GlobalContext

class ArtistRepo {

    fun getTopSongs(id : Int): Observable<Tracks> {
        var apiManager =  GlobalContext.get().koin.get<WebService>()
        return apiManager.getArtistTopSongs(id)
    }

}