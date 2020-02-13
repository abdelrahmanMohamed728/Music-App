package com.example.musicapp.Repos

import com.example.musicapp.Api.ApiManager
import com.example.musicapp.Models.Artists
import com.example.musicapp.Models.Tracks
import io.reactivex.Observable

class SearchRepo {
    companion object {
        var instance: ChartsRepo? = null
        fun init() {
            instance = ChartsRepo()
        }
    }

    fun searchSongs(name : String): Observable<Tracks> {
        return ApiManager.instance!!.searchSongs(name)
    }

    fun searchArtists(name : String) : Observable<Artists>{
        return ApiManager.instance!!.searchArtists(name)
    }
}