package com.blaccoder.travelmantics

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.blaccoder.travelmantics.model.TravelDealTimestamped
import com.blaccoder.travelmantics.ui.showShortMessage
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
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

object FirebaseRoles {
    //TODO Move to viewmodel
    private const val ADMIN_COLLECTION = "admin"

    private val _isAdmin: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val isAdmin: LiveData<Boolean>
        get() = _isAdmin


    fun isAdmin(db: FirebaseFirestore, uid: String) {
        db.collection(ADMIN_COLLECTION)
            .get()
            .addOnCompleteListener { querySnapshot ->
                for (docs in querySnapshot.result!!.documents) {
                    _isAdmin.value = uid == docs.id
                    Timber.d("${isAdmin.value}")
                }
            }
    }

}

fun logOut(context: Context) {
    AuthUI.getInstance()
        .signOut(context)
        .addOnCompleteListener {
            showShortMessage(context, "Signed Out")
        }
}

fun getTravelDeals(query: Query): FirestoreRecyclerOptions<TravelDealTimestamped> {
    return FirestoreRecyclerOptions.Builder<TravelDealTimestamped>()
        .setQuery(query, TravelDealTimestamped::class.java)
        .build()
}

val query = FirebaseFirestore.getInstance().collection(DEALS_COLLECTION).orderBy(TRAVEL_DEAL_PRICE_FIELD)

