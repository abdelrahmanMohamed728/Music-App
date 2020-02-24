package com.example.musicapp.views

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.musicapp.views.Home.HomeFragment
import com.example.musicapp.views.Search.SearchFragment

class HomeViewPagerAdapter(private val myContext: Context, fm: FragmentManager) : FragmentStatePagerAdapter(fm) {


    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> {
                HomeFragment()
            }
            1 -> {
                SearchFragment()
            }
            else -> null
        }
    }

    // this counts total number of tabs
    override fun getCount(): Int {
        return 2
    }
}