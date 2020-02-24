package com.example.musicapp.views.Home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicapp.Models.JsonResponse

import com.example.musicapp.R
import com.example.musicapp.views.Artist.ArtistFragment
import com.example.musicapp.views.Home.ChartsAdapters.HomeArtistAdapter
import com.example.musicapp.views.Home.ChartsAdapters.HomeSongAdapter
import com.example.musicapp.views.Song.SongFragment
import com.example.musicapp.views.SongFragments
import kotlinx.android.synthetic.main.fragment_artist.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.get

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() , SongFragments {
    lateinit var homeArtistAdapter: HomeArtistAdapter
    lateinit var homeSongAdater: HomeSongAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homePB.visibility = View.VISIBLE
        initViewModel()
        initRecyclerView()
        initObservers()
    }

    private fun initRecyclerView() {
        initArtistRecycler()
        initSongRecycler()
    }

    private fun initSongRecycler() {
        homeSongAdater =
            HomeSongAdapter(
                context!!,
                listOf(),
                this
            )
        topChartsSongsRV.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        topChartsSongsRV.adapter = homeSongAdater
    }

    private fun initArtistRecycler() {
        homeArtistAdapter =
            HomeArtistAdapter(
                context!!,
                listOf(),
                this
            )
        topChartsArtistsRV.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        topChartsArtistsRV.adapter = homeArtistAdapter
    }

    private fun initViewModel() {
        viewModel = get()
    }

    private fun initAdapter(json: JsonResponse) {
        initArtistAdapter(json)
        initSongAdapter(json)
    }

    private fun initSongAdapter(json: JsonResponse) {
        homeSongAdater.tracks = json.tracks.data
        homeSongAdater.notifyDataSetChanged()
    }

    private fun initArtistAdapter(json: JsonResponse) {
        homeArtistAdapter.artists = json.artists.data
        homeArtistAdapter.notifyDataSetChanged()
    }

    private fun initObservers() {
        viewModel?.getTopCharts()
        viewModel?.chartsLiveData?.observe(this, Observer { json ->
            initAdapter(json)
            homePB.visibility = View.GONE
        })
    }

    fun goToArtistFragment(index : Int){
        var frag = ArtistFragment()
        var bundle = Bundle()
        bundle.putParcelable("artist", viewModel?.chartsLiveData?.value?.artists?.data!![index])
        frag.arguments = bundle
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.frame_host,frag , tag)?.addToBackStack("")?.commit()
    }

    override fun goToSongFragment(index: Int) {
        var frag = SongFragment()
        var bundle = Bundle()
        bundle.putParcelable("track", viewModel?.chartsLiveData?.value?.tracks?.data!![index])
        frag.arguments = bundle
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.frame_host,frag , tag)?.addToBackStack("")?.commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        artistTopSongsRV.adapter = null
        topChartsSongsRV.adapter = null
    }

    companion object {
        var viewModel: HomeFragmentVM? = null
    }
}
