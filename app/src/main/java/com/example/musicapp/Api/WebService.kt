package com.example.musicapp.Api

import com.example.musicapp.Models.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WebService {
    @GET("/chart")
    fun getCharts() : Observable<JsonResponse>
    @GET("/artist/{id}/top?limit=10")
    fun getArtistTopSongs(@Path("id")id:Int) : Observable<Tracks>
    @GET("/search")
    fun search(@Query("q")name : String) : Observable<Tracks>
}