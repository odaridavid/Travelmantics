package com.blaccoder.travelmantics.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.blaccoder.travelmantics.R
import kotlinx.android.synthetic.main.fragment_travel_deals.view.*

class TravelDealFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_travel_deals, container, false)
        return v
    }

}
