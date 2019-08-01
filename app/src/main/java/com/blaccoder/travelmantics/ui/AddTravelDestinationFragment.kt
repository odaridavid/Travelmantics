package com.blaccoder.travelmantics.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.blaccoder.travelmantics.R


/**
 * A simple [Fragment] subclass.
 *
 */
class AddTravelDestinationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_add_travel, container, false)
    }


}
