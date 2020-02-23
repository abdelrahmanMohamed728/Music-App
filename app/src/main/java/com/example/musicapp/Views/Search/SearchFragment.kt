package com.example.musicapp.Views.Search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicapp.R
import com.example.musicapp.Views.Home.ChartsAdapters.HomeSongAdapter
import com.example.musicapp.Views.Home.HomeFragment
import com.example.musicapp.Views.Song.SongFragment
import com.example.musicapp.Views.SongFragments
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.core.context.GlobalContext


class SearchFragment : Fragment() ,SongFragments{

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    lateinit var SearchAdapter: HomeSongAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initListeners()
    }

    private fun initViewModel() {
        viewModel = GlobalContext.get().koin.get()
    }

    private fun initListeners() {
        searchSV.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    initSearchAdapter()
                    initSearchObserver(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    private fun initSearchObserver(artist: String) {
        viewModel?.search(artist)
        viewModel?.tracksLiveData?.observe(this, Observer {
            if (it!=null) {
                SearchAdapter.tracks = it
                SearchAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun initSearchAdapter() {
        SearchAdapter =
            HomeSongAdapter(
                context!!,
                listOf(),
                this
            )
        searchRV.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        searchRV.adapter = SearchAdapter
    }


    companion object {
        var viewModel: SearchFragmentVM? = null
    }

    override fun goToSongFragment(index: Int) {
        var frag = SongFragment()
        var bundle = Bundle()
        bundle.putParcelable("track", viewModel?.tracksLiveData?.value?.get(index))
        frag.arguments = bundle
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.search_host,frag , tag)?.addToBackStack("")?.commit()
    }
}
