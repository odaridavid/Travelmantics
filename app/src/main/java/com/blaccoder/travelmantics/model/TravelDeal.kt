package com.blaccoder.travelmantics.model

import java.sql.Timestamp
import java.util.*

/**
 * Created By David Odari
 * On 01/08/19
 *
 **/
data class TravelDeal(
    val title: String,
    val description: String,
    val price: String,
    val imageUrl: String,
    val id: String = ""
)

fun TravelDeal.withTimeStamp(): TravelDealTimestamped {
    return TravelDealTimestamped(
        title = this.title,
        description = this.description,
        price = this.price,
        imageUrl = this.imageUrl,
        id = this.id
    )
}

fun TravelDealTimestamped.asMap(): MutableMap<String, Any?> {
    return mutableMapOf(
        "title" to title,
        "description" to description,
        "price" to price,
        "id" to id,
        "imageUrl" to imageUrl,
        "timestamp" to timeStamp
    )
}

data class TravelDealTimestamped(
    var title: String? = "",
    var description: String? = "",
    var price: String? = "",
    var imageUrl: String? = "",
    val id: String? = "",
    val timeStamp: String? = Timestamp(Date().time).toString()
)
