package com.example.musicapp.Views.Artist


import android.os.Bundle
import android.os.Trace
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicapp.Models.Track

import com.example.musicapp.R
import com.example.musicapp.Views.Home.ChartsAdapters.HomeSongAdapter
import com.example.musicapp.Views.Song.SongFragment
import com.example.musicapp.Views.SongFragments
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_artist.*
import org.koin.android.ext.android.get

/**
 * A simple [Fragment] subclass.
 */
class ArtistFragment : Fragment(), SongFragments {

    lateinit var homeSongAdater: HomeSongAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_artist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        artistPB.visibility = View.VISIBLE
        initViewModel()
        extractData()
        initRecycler()
        initObservers()
    }

    private fun initRecycler() {
        homeSongAdater =
            HomeSongAdapter(
                context!!,
                listOf(),
                this
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
        viewModel?.topSongsLiveData?.observe(this, Observer { tracks ->
            homeSongAdater.tracks = tracks
            homeSongAdater.notifyDataSetChanged()
            artistPB.visibility = View.GONE
        })
    }

    private fun extractData() {
        if (arguments != null)
            viewModel?.extractData(arguments!!)
    }

    private fun initViewModel() {
        viewModel = get()
    }

    override fun goToSongFragment(index: Int) {
        var frag = SongFragment()
        var bundle = Bundle()
        var song : Track
         song = viewModel?.topSongsLiveData?.value?.get(index)!!
        song.artist = viewModel?.artistLiveData?.value!!
        bundle.putParcelable("track", song)
        frag.arguments = bundle
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.frame_host, frag, tag)?.addToBackStack("")?.commit()
    }

    companion object {
        var viewModel: ArtistFragmentVM? = null
    }

}
