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
    val id: Long = 0L
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

data class TravelDealTimestamped(
    val title: String,
    val description: String,
    val price: String,
    val imageUrl: String,
    val id: Long = 0L,
    val timeStamp: String = Timestamp(Date().time).toString()
)
