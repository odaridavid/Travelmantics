package com.blaccoder.travelmantics.services

import android.app.Activity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.blaccoder.travelmantics.RC_SIGN_IN
import com.blaccoder.travelmantics.authUiIntent
import com.blaccoder.travelmantics.ui.deals.TravelDealsListViewModel
import com.google.firebase.auth.FirebaseAuth

/**
 * Created By David Odari
 * On 04/08/19
 *
 **/
class FirebaseAuthState(activity: Activity, viewModel: ViewModel? = null) :
    LifecycleObserver {

    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    private val authStateListener: FirebaseAuth.AuthStateListener

    init {
        authStateListener = FirebaseAuth.AuthStateListener { auth ->
            if (auth.currentUser == null) {
                activity.startActivityForResult(authUiIntent(), RC_SIGN_IN)
            } else {
                (viewModel as TravelDealsListViewModel).updateButtonStatus(firebaseAuth)
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