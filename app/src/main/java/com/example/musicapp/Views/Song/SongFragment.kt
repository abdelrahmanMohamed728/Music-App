package com.example.musicapp.Views.Song


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProviders
import com.andremion.music.MusicCoverView

import com.example.musicapp.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_song.*
import java.lang.Exception

class SongFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_song, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        viewModel?.extractData(arguments!!)
        initListeners()
        initData()
    }

    private fun initData() {
        initImgs()
        initTxts()
    }

    private fun initTxts() {
        artistName.text = viewModel?.songLiveData?.value?.artist?.name
        songName.text = viewModel?.songLiveData?.value?.title
    }

    private fun initImgs() {
        Picasso.get().load(viewModel?.songLiveData?.value?.album?.cover_xl).into(songImage)
        var img = ImageView(context)
        Picasso.get().load(viewModel?.songLiveData?.value?.artist?.picture_xl)
            .into(img, object : Callback {
                override fun onSuccess() {
                    songBackground.setBackgroundDrawable(img.drawable)
                }

                override fun onError(e: Exception?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(SongFragmentVM::class.java)
    }

    private fun initListeners() {
        initMusicCover()
    }

    private fun initMusicCover() {
        songImage.setCallbacks(object : MusicCoverView.Callbacks {
            override fun onMorphEnd(coverView: MusicCoverView?) {
                if (MusicCoverView.SHAPE_CIRCLE == coverView?.shape) {
                    coverView?.start()
                }
            }

            override fun onRotateEnd(coverView: MusicCoverView?) {
                coverView?.morph()
            }

        })
        songImage.setOnClickListener {
            if (songImage.isRunning)
                songImage.stop()
            else {
                songImage.morph()
            }
        }
    }

    companion object {
        var viewModel: SongFragmentVM? = null
    }
}
