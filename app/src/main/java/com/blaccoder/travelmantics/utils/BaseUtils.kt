package com.blaccoder.travelmantics

import android.content.Context
import android.widget.Toast

/**
 * Created By David Odari
 * On 01/08/19
 *
 **/

fun Toast.showShortMessage(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun Toast.showLongMessage(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}