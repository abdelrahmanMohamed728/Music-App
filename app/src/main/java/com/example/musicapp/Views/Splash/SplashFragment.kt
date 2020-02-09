package com.example.musicapp.Views.Splash


import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.musicapp.R


class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        object : CountDownTimer(4000,1000){
            override fun onFinish() {
                Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_logInFragment)
            }

            override fun onTick(millisUntilFinished: Long) {

            }

        }.start()
    }
}
