package com.blaccoder.travelmantics.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.blaccoder.travelmantics.ui.details.TravelDealViewModel
import com.google.firebase.firestore.FirebaseFirestore

/**
 * Created By David Odari
 * On 10/08/19
 *
 **/
class ViewModelFactory(val db:FirebaseFirestore):ViewModelProvider.Factory{

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            (modelClass.isAssignableFrom(TravelDealViewModel::class.java)) -> TravelDealViewModel(db) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}