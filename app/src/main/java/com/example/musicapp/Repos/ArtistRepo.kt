package com.example.musicapp.Repos

import com.example.musicapp.Api.ApiManager
import com.example.musicapp.Models.Artist
import com.example.musicapp.Models.Tracks
import io.reactivex.Observable

class ArtistRepo {
    companion object {
        var instance: ArtistRepo? = null
        fun init() {
            instance = ArtistRepo()
        }
    }
    fun getTopSongs(id : Int): Observable<Tracks> {
        return ApiManager.instance!!.getArtistTopSongs(id)
    }

}