package com.blaccoder.travelmantics.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.blaccoder.travelmantics.R
import com.blaccoder.travelmantics.RC_SIGN_IN
import com.blaccoder.travelmantics.services.FirebaseAuthState
import com.blaccoder.travelmantics.ui.deals.TravelDealsListViewModel
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
        val viewModel = ViewModelProviders.of(this).get(TravelDealsListViewModel::class.java)

        lifecycle.addObserver(FirebaseAuthState(this, viewModel))
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK) {
                val auth = FirebaseAuth.getInstance()
            } else {
                Timber.d("${response?.error?.errorCode}")
            }
        }
    }
}
