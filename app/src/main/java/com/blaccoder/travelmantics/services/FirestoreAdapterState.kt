package com.blaccoder.travelmantics.services

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.RecyclerView
import com.blaccoder.travelmantics.model.TravelDealTimestamped
import com.blaccoder.travelmantics.ui.deals.TravelDealsFirestoreAdapter
import com.firebase.ui.firestore.FirestoreRecyclerAdapter

/**
 * Created By David Odari
 * On 10/08/19
 *
 **/
class FirestoreAdapterState(private val firestoreRecyclerAdapter: FirestoreRecyclerAdapter<TravelDealTimestamped, TravelDealsFirestoreAdapter.TravelDealViewHolder>) :
    LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun startListening() {
        firestoreRecyclerAdapter.startListening()

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stopListening() {
        firestoreRecyclerAdapter.stopListening()
    }
}