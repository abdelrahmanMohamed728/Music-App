package com.example.musicapp.View

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.musicapp.View.Home.HomeFragment

class HomeViewPagerAdapter(private val myContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    // this is for fragment tabs
    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> {
                HomeFragment()
            }
            else -> null
        }
    }

    // this counts total number of tabs
    override fun getCount(): Int {
        return 1
    }
}