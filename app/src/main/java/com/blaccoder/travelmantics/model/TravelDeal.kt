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
    val id: Long = 0L
)