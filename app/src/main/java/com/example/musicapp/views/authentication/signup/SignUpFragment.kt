package com.example.musicapp.views.authentication.signup


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.example.musicapp.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_log_in.*
import kotlinx.android.synthetic.main.fragment_log_in.emailET
import kotlinx.android.synthetic.main.fragment_sign_up.*
import org.koin.core.context.GlobalContext

/**
 * A simple [Fragment] subclass.
 */
class SignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initListeners()

    }

    private fun initListeners() {
        signUpbtn.setOnClickListener {
            signUpPB.visibility = View.VISIBLE
            signUp()
        }


    }

    private fun signUp() {
        viewModel?.signUp(
            emailET.text.toString(),
            usernameET.text.toString(),
            passwordET.text.toString(),
            this
        )
        viewModel?.successSignUpLD?.observe(this, Observer {
            signUpPB.visibility = View.INVISIBLE
            if (it) {
                goToLogIn(view!!)
            } else
                Snackbar.make(view!!, R.string.invalid_sign_up, Snackbar.LENGTH_LONG).show()
        })
    }

    private fun goToLogIn(view: View) {
        Navigation.findNavController(view)
            .navigate(
                R.id.action_signUpFragment_to_logInFragment, null, NavOptions.Builder()
                    .build()
            )
    }

    private fun initViewModel() {
        viewModel = GlobalContext.get().koin.get()
    }

    companion object {
        var viewModel: SignUpFragmentVM? = null
    }

}
