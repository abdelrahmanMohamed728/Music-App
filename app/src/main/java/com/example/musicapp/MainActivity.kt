package com.example.musicapp

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.musicapp.View.HomeActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        object : CountDownTimer(4000,1000){
            override fun onFinish() {
              val intent=Intent(this@MainActivity,
                  HomeActivity::class.java)
                startActivity(intent)
                finishAffinity()
            }

            override fun onTick(millisUntilFinished: Long) {

            }

        }.start()

    }
}
