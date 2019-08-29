package com.blaccoder.travelmantics.model

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
