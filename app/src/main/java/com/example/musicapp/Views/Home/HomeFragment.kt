package com.example.musicapp.Views.Home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicapp.Models.JsonResponse

import com.example.musicapp.R
import com.example.musicapp.Views.Artist.ArtistFragment
import com.example.musicapp.Views.Home.ChartsAdapters.HomeArtistAdapter
import com.example.musicapp.Views.Home.ChartsAdapters.HomeSongAdapter
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
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
                listOf()
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
        viewModel = ViewModelProviders.of(this).get(HomeFragmentVM::class.java)
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

        })
    }

    fun goToArtistFragment(index : Int){
        var frag = ArtistFragment()
        var bundle = Bundle()
        bundle.putParcelable("artist", viewModel?.chartsLiveData?.value?.artists?.data!![index])
        frag.arguments = bundle
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.baseLayout,frag , tag)?.addToBackStack("")?.commit()
    }

    companion object {
        var viewModel: HomeFragmentVM? = null
    }
}
