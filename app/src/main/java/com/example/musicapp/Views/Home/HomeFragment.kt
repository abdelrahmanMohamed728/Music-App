package com.example.musicapp.Views.Home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicapp.Models.Artist
import com.example.musicapp.Models.Artists
import com.example.musicapp.Models.JsonResponse

import com.example.musicapp.R
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
   lateinit var adapter : HomeArtistAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initRecyclerView()
        initObservers()
    }

    private fun initRecyclerView() {
        adapter = HomeArtistAdapter(context!!, listOf())
        topChartsArtistsRV.layoutManager =LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL ,false)
        topChartsArtistsRV.adapter = adapter
    }

    private fun initViewModel() {
         viewModel = ViewModelProviders.of(this).get(HomeFragmentVM::class.java)
    }

    private fun initAdapter(json : JsonResponse){
        adapter.artists = json.artists.data
        adapter.notifyDataSetChanged()
    }

    private fun initObservers() {
        viewModel?.getTopCharts()
        viewModel?.chartsLiveData?.observe(this, Observer{
                json ->  initAdapter(json)

        })
    }
    companion object{
        var viewModel : HomeFragmentVM ?= null
    }
}
