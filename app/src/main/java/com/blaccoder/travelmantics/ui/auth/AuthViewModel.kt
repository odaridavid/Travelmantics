package com.blaccoder.travelmantics.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created By David Odari
 * On 03/08/19
 *
 **/
class AuthViewModel : ViewModel() {

    private val _isReadyToAuthenticate: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }

    val isReadyToAuthenticate: LiveData<Boolean>
        get() = _isReadyToAuthenticate

    private val _email: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    private val _password: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    private val _hasEmail: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }

    private val _hasPassword: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }


    init {
//        Default Setup
        hasNoPassword()
        hasNoEmail()
        changeSignInButtonState()
    }

    fun changeSignInButtonState() {
        _isReadyToAuthenticate.value = (_hasEmail.value!! && _hasPassword.value!!)
    }

    fun hasPassword(password: String) {
        _hasPassword.value = true
        _password.value = password
    }

    fun hasNoPassword() {
        _hasPassword.value = false
    }

    fun hasEmail(email: String) {
        _hasEmail.value = true
        _email.value = email
    }

    fun hasNoEmail() {
        _hasEmail.value = false
    }


    fun login() {

    }

    fun signUp() {

    }

}