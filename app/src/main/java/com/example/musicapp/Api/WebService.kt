package com.example.musicapp.Api

import com.example.musicapp.Models.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface WebService {
    @GET("/chart")
    fun getCharts() : Observable<JsonResponse>
    @GET("/artist/{id}/top?limit=10")
    fun getArtistTopSongs(@Path("id")id:Int) : Observable<Tracks>
    @GET("/search?q=artist:{name}")
    fun searchArtists(@Path("name")name : String) : Observable<Artists>
    @GET("/search?q=track:{name}")
    fun searchSongs(@Path("name")name : String) : Observable<Tracks>
}