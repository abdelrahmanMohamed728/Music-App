package com.example.musicapp.Repos

import com.example.musicapp.Api.ApiManager
import com.example.musicapp.Models.JsonResponse
import io.reactivex.Observable

class ChartsRepo  {
    companion object {
         var instance: ChartsRepo? = null
        fun init() {
            instance = ChartsRepo()
        }
    }
    fun getTopCharts(): Observable<JsonResponse> {
        return ApiManager.instance!!.getCharts()
    }
}