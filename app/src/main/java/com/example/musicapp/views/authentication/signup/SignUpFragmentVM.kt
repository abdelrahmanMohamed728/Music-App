package com.example.musicapp.views.authentication.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.musicapp.Repos.FirebaseRepo

class SignUpFragmentVM(var repo : FirebaseRepo) : ViewModel() {
    var successSignUpLD = MutableLiveData<Boolean>()

    fun signUp(email: String,username : String , password : String,fragment: SignUpFragment){
        repo.SignOut(email,username,password)
        repo.successSignUpLD.observe(fragment, Observer {
            successSignUpLD.postValue(it)
        })

    }
}