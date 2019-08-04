package com.blaccoder.travelmantics.ui

import android.content.Context
import android.widget.Toast

/**
 * Created By David Odari
 * On 01/08/19
 *
 **/

fun showShortMessage(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun showLongMessage(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}