package com.example.musicapp.Views.Home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicapp.Models.JsonResponse
import com.example.musicapp.Repos.ChartsRepo
import io.reactivex.schedulers.Schedulers

class HomeFragmentVM(var repo : ChartsRepo) : ViewModel() {
    var chartsLiveData = MutableLiveData<JsonResponse>()

    fun getTopCharts() {
        var observable = repo.getTopCharts()
            ?.subscribeOn(Schedulers.io())
            ?.doOnNext { chartsLiveData.postValue(it) }
        observable?.subscribe({ Log.d("myTag", "observable.subscribe") },
            { throwable ->
                Log.d("myTag", "observable.throwable: $throwable")
            })
    }
}