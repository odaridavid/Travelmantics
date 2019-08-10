package com.blaccoder.travelmantics.ui.deals

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blaccoder.travelmantics.FirebaseRoles
import com.blaccoder.travelmantics.FirebaseRoles.isAdmin
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import timber.log.Timber

/**
 * Created By David Odari
 * On 05/08/19
 *
 **/
class TravelDealsListViewModel(private val db: FirebaseFirestore) : ViewModel() {

    private val _displayButton: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val displayButton: LiveData<Boolean>
        get() = _displayButton

    init {
        _displayButton.value = false
        val firebaseAuth = FirebaseAuth.getInstance()
        if (firebaseAuth.currentUser != null) {
            updateButtonStatus(firebaseAuth)
        }
    }

    fun updateButtonStatus(firebaseAuth: FirebaseAuth) {
        Timber.d("Update Button State for ${firebaseAuth.uid}")
        isAdmin(db, firebaseAuth.uid!!)
        Timber.d("is Admin:${_displayButton.value}")
    }


}