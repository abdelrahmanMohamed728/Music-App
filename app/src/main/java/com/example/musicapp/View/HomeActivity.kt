package com.example.musicapp.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.musicapp.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        overridePendingTransition(
            R.anim.slidein,
            R.anim.slideout
        )
      var imageView = ImageView(this)
        imageView.setImageResource(R.drawable.ic_home)
        homeTabLayout!!.addTab(homeTabLayout!!.newTab().setCustomView(imageView))
        val adapter = HomeViewPagerAdapter(this, supportFragmentManager)
        homeViewPager!!.adapter = adapter
    }
}
