package com.blaccoder.travelmantics.ui.auth

import android.os.Bundle
import android.text.Editable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.blaccoder.travelmantics.R
import com.blaccoder.travelmantics.TextWatcherImpl
import com.blaccoder.travelmantics.closeKeyboard
import kotlinx.android.synthetic.main.activity_authentication.*
import timber.log.Timber

class AuthenticationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)
        val authViewModel = ViewModelProviders.of(this)[AuthViewModel::class.java]
        email_edit_text.addTextChangedListener(object : TextWatcherImpl {
            override fun onTextChanged(chararr: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!chararr.isNullOrBlank()) {
                    Timber.d("Got Text from $chararr")
                    authViewModel.hasEmail(email_edit_text.text.toString())
                    authViewModel.changeSignInButtonState()
                    sign_in_button.isEnabled = authViewModel.isReadyToAuthenticate.value!!

                }
            }

            override fun afterTextChanged(s: Editable?) {
                super.afterTextChanged(s)
                if (s.toString().isEmpty()) {
                    authViewModel.hasNoEmail()
                    authViewModel.changeSignInButtonState()
                    sign_in_button.isEnabled = authViewModel.isReadyToAuthenticate.value!!
                    closeKeyboard(this@AuthenticationActivity, email_edit_text)
                }
            }
        })
        password_edit_text.addTextChangedListener(object : TextWatcherImpl {
            override fun onTextChanged(chararr: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!chararr.isNullOrBlank()) {
                    authViewModel.hasPassword(password_edit_text.text.toString())
                    authViewModel.changeSignInButtonState()
                    sign_in_button.isEnabled = authViewModel.isReadyToAuthenticate.value!!
                }
            }

            override fun afterTextChanged(s: Editable?) {
                super.afterTextChanged(s)
                if (s.toString().isEmpty()) {
                    authViewModel.hasNoPassword()
                    authViewModel.changeSignInButtonState()
                    sign_in_button.isEnabled = authViewModel.isReadyToAuthenticate.value!!
                    closeKeyboard(this@AuthenticationActivity, password_edit_text)
                }
            }
        })

    }

}
