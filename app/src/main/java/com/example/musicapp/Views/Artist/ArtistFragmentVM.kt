package com.example.musicapp.Views.Artist

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicapp.Models.Artist
import com.example.musicapp.Models.Track
import com.example.musicapp.Repos.ArtistRepo
import io.reactivex.schedulers.Schedulers
import org.koin.core.context.GlobalContext

class ArtistFragmentVM : ViewModel() {
     var artistLiveData = MutableLiveData<Artist>()
     var topSongsLiveData = MutableLiveData<List<Track>>()

    fun extractData(bundle: Bundle){
          var artist = bundle.get("artist") as Artist
          artistLiveData.value = artist
      }

      fun getTopSongs(){
          var repo = GlobalContext.get().koin.get<ArtistRepo>()
          var observable = repo.getTopSongs(artistLiveData.value?.id!!)
              ?.subscribeOn(Schedulers.io())
              ?.doOnNext { topSongsLiveData.postValue(it.data)
                  Log.v("myTag", it.data.size.toString())
              }
          observable?.subscribe({ Log.d("myTag", "observable.subscribe") },
              { throwable ->
                  Log.d("myTag", "observable.throwable: $throwable")
              })
      }
}