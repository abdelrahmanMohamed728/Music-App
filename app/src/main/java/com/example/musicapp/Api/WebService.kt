package com.example.musicapp.Api

import com.example.musicapp.Models.JsonResponse
import com.example.musicapp.Models.Track
import com.example.musicapp.Models.Tracks
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface WebService {
    @GET("/chart")
    fun getCharts() : Observable<JsonResponse>
    @GET("/artist/{id}/top?limit=10")
    fun getArtistTopSongs(@Path("id")id:Int) : Observable<Tracks>

}