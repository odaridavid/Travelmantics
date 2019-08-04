package com.blaccoder.travelmantics.ui.deals


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.blaccoder.travelmantics.R
import kotlinx.android.synthetic.main.fragment_travel_deals.view.*

class TravelDealsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_travel_deals, container, false)
        v.travel_deal_fab.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_travelDestinationsFragment_to_addTravelDestinationFragment)
        }
        return v
    }

}
