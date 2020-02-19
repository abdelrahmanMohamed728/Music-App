package com.example.musicapp.Repos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth

class FirebaseRepo {
    private lateinit var auth: FirebaseAuth
    var successLogInLD = MutableLiveData<Boolean>()
    fun SignIn(email: String, password: String): Boolean {
        auth = FirebaseAuth.getInstance()
        var success = false
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    success = true
                    successLogInLD.value = true
                }
                else {
                    successLogInLD.value = false
                    // If sign in fails, display a message to the user.
                    Log.w("myTag", "signInWithEmail:failure", task.exception)

                }
            }

        return success
    }

}