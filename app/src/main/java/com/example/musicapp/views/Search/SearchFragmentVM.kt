package com.example.musicapp.views.Search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicapp.Models.Track
import com.example.musicapp.Repos.SearchRepo
import io.reactivex.schedulers.Schedulers

class SearchFragmentVM(var repo : SearchRepo) : ViewModel() {

    var tracksLiveData = MutableLiveData<List<Track>>()

    fun search(track : String){
        var observable = repo.search(track)
            ?.subscribeOn(Schedulers.io())
            ?.doOnNext { tracksLiveData.postValue(it.data) }
        observable?.subscribe({ Log.d("myTag", "observable.subscribe") },
            { throwable ->
                Log.d("myTag", "observable.throwable: $throwable")
            })
    }
}