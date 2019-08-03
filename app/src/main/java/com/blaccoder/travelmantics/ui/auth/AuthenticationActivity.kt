package com.blaccoder.travelmantics.ui.auth

import android.os.Bundle
import android.text.Editable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.blaccoder.travelmantics.R
import com.blaccoder.travelmantics.TextWatcherImpl
import com.blaccoder.travelmantics.closeKeyboard
import kotlinx.android.synthetic.main.activity_authentication.*

class AuthenticationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)
        val authViewModel = ViewModelProviders.of(this)[AuthViewModel::class.java]
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
                    closeKeyboard(this@AuthenticationActivity, email_edit_text)
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
                    closeKeyboard(this@AuthenticationActivity, password_edit_text)
                }
            }
        })

    }

    private fun updateSignInButton(authViewModel: AuthViewModel) {
        authViewModel.changeSignInButtonState()
        sign_in_button.isEnabled = authViewModel.isReadyToAuthenticate.value!!
    }

}
