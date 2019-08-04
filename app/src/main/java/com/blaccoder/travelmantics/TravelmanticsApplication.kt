package com.blaccoder.travelmantics

import android.app.Application
import timber.log.Timber

/**
 * Created By David Odari
 * On 01/08/19
 *
 **/
class TravelmanticsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}