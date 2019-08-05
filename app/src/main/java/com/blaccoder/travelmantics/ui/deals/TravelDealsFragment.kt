package com.blaccoder.travelmantics.ui.deals


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.blaccoder.travelmantics.R
import com.blaccoder.travelmantics.ui.showShortMessage
import com.firebase.ui.auth.AuthUI
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
        val viewModel = ViewModelProviders.of(this).get(TravelDealsViewModel::class.java)
        viewModel.displayButton.observe(this, Observer { isAdmin ->
            if (isAdmin) {
                v.travel_deal_fab.visibility = View.VISIBLE
            } else {
                v.travel_deal_fab.visibility = View.GONE
            }
        })
        setHasOptionsMenu(true)
        return v
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_logout) {
            AuthUI.getInstance()
                .signOut(context!!)
                .addOnCompleteListener {
                    showShortMessage(context!!, "Signed Out")
                }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

}
