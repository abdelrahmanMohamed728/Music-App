package com.example.musicapp.views.authentication.login


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.example.musicapp.R
import com.example.musicapp.views.HomeActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_log_in.*
import org.koin.android.ext.android.get


class LogInFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_log_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initListeners()
    }

    private fun initViewModel() {
        viewModel = get()
    }

    private fun initListeners() {
        logInBtn.setOnClickListener {
           logIn()
        }

        signUpBtn.setOnClickListener {
            openSignUpFrag(view!!)
        }
    }

    private fun logIn() {
        logInPB.visibility = View.VISIBLE
        if (viewModel?.isEmailValid(emailET.text.toString())!!) {
            viewModel?.signIn(emailET.text.toString(), passworddET.text.toString(), this)!!
            viewModel!!.successfulLogIn.observe(this, Observer {
                if (it) {
                    logInPB.visibility = View.GONE
                    startActivity(Intent(activity, HomeActivity::class.java))
                    activity?.finishAffinity()
                } else {
                    logInPB.visibility = View.GONE
                    Snackbar.make(
                        view!!,
                        getString(R.string.failedLogIn),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            })

        } else {
            logInPB.visibility = View.GONE
            Snackbar.make(
                view!!,
                getString(R.string.invalid_email_format),
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    fun openSignUpFrag(view : View){
        Navigation.findNavController(view)
            .navigate(
                R.id.action_logInFragment_to_signUpFragment, null, NavOptions.Builder()
                    .setPopUpTo(R.id.logInFragment, false)
                    .build()
            )
    }

    companion object {
        var viewModel: LoginFragmentVM? = null
    }
}
