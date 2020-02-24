package com.example.musicapp.Repos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth

class FirebaseRepo {
    private lateinit var auth: FirebaseAuth
    var successLogInLD = MutableLiveData<Boolean>()
    var successSignUpLD = MutableLiveData<Boolean>()
    fun SignIn(email: String, password: String) {
        auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    successLogInLD.value = true
                } else {
                    successLogInLD.value = false
                    // If sign in fails, display a message to the user.
                    Log.w("myTag", "signInWithEmail:failure", task.exception)

                }
            }
    }

    fun SignOut(email: String, username: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                   successSignUpLD.postValue(true)
                } else {
                    successSignUpLD.postValue(false)
                }

            }
    }

}