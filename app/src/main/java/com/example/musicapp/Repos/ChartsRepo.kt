package com.example.musicapp.Repos

import com.example.musicapp.Api.WebService
import com.example.musicapp.Models.JsonResponse
import io.reactivex.Observable
import org.koin.core.context.GlobalContext

class ChartsRepo  {
    fun getTopCharts(): Observable<JsonResponse> {
        var apiManager =  GlobalContext.get().koin.get<WebService>()
        return apiManager.getCharts()
    }
}