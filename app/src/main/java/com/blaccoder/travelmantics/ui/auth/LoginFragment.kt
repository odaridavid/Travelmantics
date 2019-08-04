package com.blaccoder.travelmantics.ui.auth


import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.blaccoder.travelmantics.R
import com.blaccoder.travelmantics.TextWatcherImpl
import com.blaccoder.travelmantics.closeKeyboard
import com.blaccoder.travelmantics.utils.FirebaseUtils
import kotlinx.android.synthetic.main.fragment_login.*
import timber.log.Timber

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val authViewModel = ViewModelProviders.of(this)[AuthViewModel::class.java]
        authViewModel.isLoggedIn.observe(this, Observer { loggedIn ->
            Timber.d("is logged $loggedIn")
            if (loggedIn) {
                Timber.d(FirebaseUtils.getUser()?.email)
//                startActivity(Intent(this@AuthenticationActivity, MainActivity::class.java))
//                finish()
            }
        })
        email_edit_text.addTextChangedListener(object : TextWatcherImpl {
            override fun onTextChanged(chararr: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!chararr.isNullOrBlank()) {
                    authViewModel.hasEmail(email_edit_text.text.toString())
                    updateSignInButton(authViewModel)
                }
            }

            override fun afterTextChanged(s: Editable?) {
                super.afterTextChanged(s)
                if (s.toString().isEmpty()) {
                    authViewModel.hasNoEmail()
                    updateSignInButton(authViewModel)
                    closeKeyboard(context!!, email_edit_text)
                }
            }
        })
        password_edit_text.addTextChangedListener(object : TextWatcherImpl {
            override fun onTextChanged(chararr: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!chararr.isNullOrBlank()) {
                    authViewModel.hasPassword(password_edit_text.text.toString())
                    updateSignInButton(authViewModel)
                }
            }

            override fun afterTextChanged(s: Editable?) {
                super.afterTextChanged(s)
                if (s.toString().isEmpty()) {
                    authViewModel.hasNoPassword()
                    updateSignInButton(authViewModel)
                    closeKeyboard(context!!, password_edit_text)
                }
            }
        })
        authenticateUser()
        return view
    }

    private fun updateSignInButton(authViewModel: AuthViewModel) {
        authViewModel.changeSignInButtonState()
        sign_in_button.isEnabled = authViewModel.isReadyToAuthenticate.value!!
    }

    fun authenticateUser() {
        sign_in_button.setOnClickListener {
            FirebaseUtils.signIn(this, email_edit_text.text.toString(), password_edit_text.text.toString())
        }
    }
}
