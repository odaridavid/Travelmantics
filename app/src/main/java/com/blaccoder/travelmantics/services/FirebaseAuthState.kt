package com.blaccoder.travelmantics.services

import android.app.Activity
import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.blaccoder.travelmantics.RC_SIGN_IN
import com.blaccoder.travelmantics.authUiIntent
import com.blaccoder.travelmantics.ui.deals.TravelDealsViewModel
import com.blaccoder.travelmantics.ui.showShortMessage
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

/**
 * Created By David Odari
 * On 04/08/19
 *
 **/
class FirebaseAuthState(activity: Activity, viewModel: ViewModel) :
    LifecycleObserver {

    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    private val authStateListener: FirebaseAuth.AuthStateListener

    init {
        val db = FirebaseFirestore.getInstance()

        authStateListener = FirebaseAuth.AuthStateListener { auth ->
            if (auth.currentUser == null) {
                activity.startActivityForResult(authUiIntent(), RC_SIGN_IN)
            } else {
                (viewModel as TravelDealsViewModel).updateButtonStatus(firebaseAuth, db)
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun startListening() {
        firebaseAuth.addAuthStateListener(authStateListener)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stopListening() {
        firebaseAuth.removeAuthStateListener(authStateListener)
    }

}