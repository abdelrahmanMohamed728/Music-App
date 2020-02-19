package com.example.musicapp.Views.LogIn

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.musicapp.Repos.FirebaseRepo
import org.koin.core.context.GlobalContext

class LoginFragmentVM : ViewModel() {

    var successfulLogIn = MutableLiveData<Boolean>()
    fun isEmailValid(email : CharSequence) :Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun signIn(email : String , password : String,fragment: LogInFragment)  {
        var repo = GlobalContext.get().koin.get<FirebaseRepo>()
        repo.SignIn(email,password)!!
        repo.successLogInLD.observe( fragment, Observer {
           successfulLogIn.value = it
        })
    }
}