package com.blaccoder.travelmantics

import android.content.Intent
import com.blaccoder.travelmantics.model.TravelDeal
import com.firebase.ui.auth.AuthUI
import com.google.firebase.firestore.FirebaseFirestore
import timber.log.Timber

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

object FirebaseRoles {

    private var isAdmin = false

    fun checkAdminStatus(db: FirebaseFirestore, uid: String): Boolean {
//        TODO Decrease time complexity
        db.collection("admin")
            .addSnapshotListener { querySnapshot, _ ->
                if (!querySnapshot!!.isEmpty) {
                    for (x in querySnapshot.documents) {
                        if (x.id.equals(uid)) {
                            isAdmin = true
                            Timber.d("Is Admin")
                        } else {
                            isAdmin = false
                        }
                    }
                }
            }
        return isAdmin
    }
}

