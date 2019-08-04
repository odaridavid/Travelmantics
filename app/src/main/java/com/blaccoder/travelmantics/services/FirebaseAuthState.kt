package com.blaccoder.travelmantics.services

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.google.firebase.auth.FirebaseAuth

/**
 * Created By David Odari
 * On 04/08/19
 *
 **/
class FirebaseAuthState(val firebaseAuth: FirebaseAuth, val authStateListener: FirebaseAuth.AuthStateListener) :
    LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun startListening() {
        firebaseAuth.addAuthStateListener(authStateListener)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stopListening() {
        firebaseAuth.removeAuthStateListener(authStateListener)
    }
}