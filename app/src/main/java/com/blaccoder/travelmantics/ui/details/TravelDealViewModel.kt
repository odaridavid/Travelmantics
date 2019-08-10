package com.blaccoder.travelmantics.ui.details

import androidx.lifecycle.ViewModel
import com.blaccoder.travelmantics.model.TravelDeal
import com.blaccoder.travelmantics.model.withTimeStamp
import com.google.firebase.firestore.FirebaseFirestore
import timber.log.Timber


/**
 * Created By David Odari
 * On 10/08/19
 *
 **/
private const val DEALS_COLLECTION = "deals"

class TravelDealViewModel(private val db: FirebaseFirestore) : ViewModel() {

    fun saveToFirestore(deal: TravelDeal) {
        db.collection(DEALS_COLLECTION)
            .document(deal.id.toString())
            .set(deal.withTimeStamp())
            .addOnSuccessListener {
                Timber.d(TravelDealViewModel::class.java.simpleName, "Successfully Added")
            }
            .addOnFailureListener { e ->
                Timber.d(TravelDealViewModel::class.java.simpleName, e.toString())
            }
    }

    fun removeFromFirestore(id: String) {
        db.collection(DEALS_COLLECTION)
            .document(id)
            .delete()
            .addOnSuccessListener {
                Timber.d(TravelDealViewModel::class.java.simpleName, "Successfully Deleted")
            }
            .addOnFailureListener { e ->
                Timber.d(TravelDealViewModel::class.java.simpleName, e.toString())
            }
    }
}