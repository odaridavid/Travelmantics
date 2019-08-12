package com.blaccoder.travelmantics.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.sql.Timestamp
import java.util.*

/**
 * Created By David Odari
 * On 11/08/19
 *
 **/
@Parcelize
data class TravelDealTimestamped(
    var title: String? = "",
    var description: String? = "",
    var price: String? = "",
    var imageUrl: String? = "",
    val id: String? = "",
    val timeStamp: String? = Timestamp(Date().time).toString()
): Parcelable

fun TravelDealTimestamped.asMap(): MutableMap<String, Any?> {
    return mutableMapOf(
        "title" to title,
        "description" to description,
        "price" to price,
        "id" to id,
        "imageUrl" to imageUrl,
        "timeStamp" to timeStamp
    )
}