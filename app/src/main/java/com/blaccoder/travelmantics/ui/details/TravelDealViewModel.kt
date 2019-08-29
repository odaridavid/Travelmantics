package com.blaccoder.travelmantics.ui.details

import androidx.lifecycle.ViewModel
import com.blaccoder.travelmantics.DEALS_COLLECTION
import com.blaccoder.travelmantics.model.TravelDeal
import com.blaccoder.travelmantics.model.TravelDealTimestamped
import com.blaccoder.travelmantics.model.asMap
import com.blaccoder.travelmantics.model.withTimeStamp
import com.google.firebase.firestore.FirebaseFirestore
import timber.log.Timber


/**
 * Created By David Odari
 * On 10/08/19
 *
 **/
class TravelDealViewModel(private val db: FirebaseFirestore) : ViewModel() {

    fun saveToFirestore(deal: TravelDeal) {
        db.collection(DEALS_COLLECTION)
            .document()
            .set(deal.withTimeStamp())
            .addOnSuccessListener { Timber.d("Successfully Added") }
            .addOnFailureListener { e -> Timber.d(e.toString()) }
    }

    fun removeFromFirestore(id: String) {
        db.collection(DEALS_COLLECTION)
            .document(id)
            .delete()
            .addOnSuccessListener { Timber.d("Successfully Deleted") }
            .addOnFailureListener { e -> Timber.d(e.toString()) }
    }

    fun updateToFirestore(newDeal: TravelDealTimestamped, dealId: String) {
        db.collection(DEALS_COLLECTION)
            .document(dealId)
            .update(newDeal.asMap())
            .addOnSuccessListener { Timber.d("Successfully Updated") }
            .addOnFailureListener { e -> Timber.d(e.toString()) }
    }
}