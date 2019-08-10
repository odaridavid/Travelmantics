package com.blaccoder.travelmantics.services

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.RecyclerView
import com.blaccoder.travelmantics.model.TravelDealTimestamped
import com.firebase.ui.firestore.FirestoreRecyclerAdapter

/**
 * Created By David Odari
 * On 10/08/19
 *
 **/
class FirestoreAdapterState(val firestoreRecyclerAdapter: FirestoreRecyclerAdapter<TravelDealTimestamped, in RecyclerView.ViewHolder>) {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun startListening() {
        firestoreRecyclerAdapter.startListening()

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stopListening() {
        firestoreRecyclerAdapter.stopListening()
    }
}