package com.example.musicapp

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.musicapp.Api.ApiManager
import com.example.musicapp.Repos.ArtistRepo
import com.example.musicapp.Repos.ChartsRepo
import com.example.musicapp.Views.HomeActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initSingeltons()
    }

    override fun onSupportNavigateUp() =
        findNavController(splashFragment).navigateUp()

    private fun initSingeltons() {
        ApiManager.init()
        ChartsRepo.init()
        ArtistRepo.init()
    }
}
