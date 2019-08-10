package com.blaccoder.travelmantics

import android.content.Context
import android.content.Intent
import com.blaccoder.travelmantics.ui.showShortMessage
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore

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

object FirebaseRoles {

    private const val ADMIN_COLLECTION = "admin"
    var isAdmin:Boolean = false

    fun isAdmin(db: FirebaseFirestore, uid: String): Boolean? {
        db.collection(ADMIN_COLLECTION)
            .get()
            .addOnSuccessListener(OnSuccessListener { admins ->
                if (admins.isEmpty) return@OnSuccessListener
                for (admin in admins) {
                    isAdmin = admin.id == uid
                }
            })
        return isAdmin
    }
}

fun logOut(context: Context) {
    AuthUI.getInstance()
        .signOut(context)
        .addOnCompleteListener {
            showShortMessage(context, "Signed Out")
        }
}

