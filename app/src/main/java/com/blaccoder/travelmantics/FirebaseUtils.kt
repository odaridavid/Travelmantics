package com.blaccoder.travelmantics

import android.content.Intent
import com.blaccoder.travelmantics.model.TravelDeal
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

fun authUiIntent(): Intent {
    return AuthUI.getInstance()
        .createSignInIntentBuilder()
        .setAvailableProviders(providers)
        .setIsSmartLockEnabled(true)
        .build()
}

fun saveToFirestore(deal: TravelDeal) {

}

fun removeFromFirestore(id: Long) {

}
