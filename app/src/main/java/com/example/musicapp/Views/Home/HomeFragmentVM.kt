package com.example.musicapp.Views.Home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicapp.Models.JsonResponse
import com.example.musicapp.Repos.ChartsRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeFragmentVM : ViewModel() {
    var chartsLiveData = MutableLiveData<JsonResponse>()

    fun getTopCharts() {
        var observable = ChartsRepo.instance?.getTopCharts()
            ?.subscribeOn(Schedulers.io())
            ?.doOnNext { chartsLiveData.postValue(it) }
        observable?.subscribe({ Log.d("myTag", "observable.subscribe") },
            { throwable ->
                Log.d("myTag", "observable.throwable: $throwable")
            })
    }
}