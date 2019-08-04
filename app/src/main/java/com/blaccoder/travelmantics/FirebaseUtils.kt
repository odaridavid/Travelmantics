package com.blaccoder.travelmantics

import com.firebase.ui.auth.AuthUI

/**
 * Created By David Odari
 * On 01/08/19
 *
 **/

const val RC_SIGN_IN = 1000

val providers = arrayListOf(
    AuthUI.IdpConfig.EmailBuilder().build(),
    AuthUI.IdpConfig.PhoneBuilder().build(),
    AuthUI.IdpConfig.GoogleBuilder().build()
)