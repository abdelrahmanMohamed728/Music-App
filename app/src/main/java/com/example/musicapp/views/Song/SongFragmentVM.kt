package com.example.musicapp.views.Song

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicapp.Models.Track

class SongFragmentVM : ViewModel() {

    var songLiveData = MutableLiveData<Track>()

    fun extractData(bundle: Bundle){
        songLiveData.value = bundle.get("track") as Track
    }
}