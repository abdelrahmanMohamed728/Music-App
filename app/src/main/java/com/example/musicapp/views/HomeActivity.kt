package com.example.musicapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.musicapp.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        addAnimation()
        createTabs()
        initViewPagerAdapter()
    }

    private fun initViewPagerAdapter() {
        val adapter = HomeViewPagerAdapter(this, supportFragmentManager)
        homeViewPager!!.adapter = adapter
        homeViewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(homeTabLayout))
        homeTabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                homeViewPager!!.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

    private fun addAnimation() {
        overridePendingTransition(
            R.anim.slidein,
            R.anim.slideout
        )
    }

    private fun createTabs() {
        var homeImageView = ImageView(this)
        homeImageView.setImageResource(R.drawable.ic_home)
        homeTabLayout!!.addTab(homeTabLayout!!.newTab().setCustomView(homeImageView),0)
        var searchImageView = ImageView(this)
        searchImageView.setImageResource(R.drawable.ic_search)
        homeTabLayout!!.addTab(homeTabLayout!!.newTab().setCustomView(searchImageView),1)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

}
