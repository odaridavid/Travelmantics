package com.blaccoder.travelmantics.model

/**
 * Created By David Odari
 * On 01/08/19
 *
 **/
data class TravelDeal(
    val id: Long = 0L,
    val title: String,
    val description: String,
    val price: String,
    val imageUrl: String
)