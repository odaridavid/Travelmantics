package com.blaccoder.travelmantics.ui.deals

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blaccoder.travelmantics.FirebaseRoles.checkAdminStatus
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import timber.log.Timber

/**
 * Created By David Odari
 * On 05/08/19
 *
 **/
class TravelDealsViewModel : ViewModel() {
    private val _displayButton: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val displayButton: LiveData<Boolean>
        get() = _displayButton

    init {
        _displayButton.value = false
        val firebaseAuth = FirebaseAuth.getInstance()
        if (firebaseAuth.currentUser != null) {
            val db = FirebaseFirestore.getInstance()
            _displayButton.value = checkAdminStatus(db, firebaseAuth.uid!!)
        }
    }

    fun updateButtonStatus(firebaseAuth: FirebaseAuth, db: FirebaseFirestore) {
        Timber.d("Update Button State")
        _displayButton.value = checkAdminStatus(db, firebaseAuth.uid!!)
        Timber.d("${_displayButton.value}")
    }

}