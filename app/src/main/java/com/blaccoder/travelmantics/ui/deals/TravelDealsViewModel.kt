package com.blaccoder.travelmantics.ui.deals

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blaccoder.travelmantics.FirebaseRoles.isAdmin
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
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
            updateButtonStatus(firebaseAuth, db)
        }
    }

    fun updateButtonStatus(firebaseAuth: FirebaseAuth, db: FirebaseFirestore) {
        var isAdmin = false
        viewModelScope.launch {
            val adminStatus = async(Dispatchers.IO) {
                Timber.d("Update Button State for ${firebaseAuth.uid}")
                isAdmin = isAdmin(db, firebaseAuth.uid!!)!!
                Timber.d("is Admin:${_displayButton.value}")
            }
            adminStatus.await()
            _displayButton.value = isAdmin
        }

    }

}