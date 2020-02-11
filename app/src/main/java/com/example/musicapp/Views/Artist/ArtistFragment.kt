package com.example.musicapp.Views.Artist


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.musicapp.R
import com.example.musicapp.Views.Home.ChartsAdapters.HomeSongAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_artist.*

/**
 * A simple [Fragment] subclass.
 */
class ArtistFragment : Fragment() {

    lateinit var homeSongAdater: HomeSongAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_artist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        extractData()
        initRecycler()
        initObservers()
    }

    private fun initRecycler() {
        homeSongAdater =
            HomeSongAdapter(
                context!!,
                listOf()
            )
        artistTopSongsRV.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        artistTopSongsRV.adapter = homeSongAdater
    }

    private fun initObservers() {

         viewModel?.artistLiveData?.observe(this, Observer {
             Picasso.get().load(it.picture_xl).into(artistImage)
             artistName.text = it.name
        })
        viewModel?.getTopSongs()
        viewModel?.topSongsLiveData?.observe(this, Observer {
            tracks -> homeSongAdater.tracks = tracks
            homeSongAdater.notifyDataSetChanged()
        })
    }

    private fun extractData() {
        if (arguments!=null)
        viewModel?.extractData(arguments!!)
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(ArtistFragmentVM::class.java)
    }

    companion object{
        var viewModel : ArtistFragmentVM? = null
    }

}
