package com.example.musicapp.Api

import com.example.musicapp.Models.JsonResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface WebService {
    @GET("/chart")
    fun getCharts() : Observable<JsonResponse>
}