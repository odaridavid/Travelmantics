package com.blaccoder.travelmantics.ui.deals


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.blaccoder.travelmantics.*
import com.blaccoder.travelmantics.services.FirestoreAdapterState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_travel_deals_list.view.*
import timber.log.Timber

class TravelDealsListFragment : Fragment() {


    private lateinit var travelDealsFirestoreAdapter: TravelDealsFirestoreAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (FirebaseAuth.getInstance().currentUser != null) {
            travelDealsFirestoreAdapter = TravelDealsFirestoreAdapter(getTravelDeals(query))
            Timber.d("User exists")
            lifecycle.addObserver(FirestoreAdapterState(travelDealsFirestoreAdapter))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_travel_deals_list, container, false)
        v.travel_deal_fab.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_travelDestinationsFragment_to_addTravelDestinationFragment)
        }
        FirebaseRoles.isAdmin.observe(this, Observer { isAdmin ->
            if (isAdmin) {
                v.travel_deal_fab.visibility = View.VISIBLE
            } else {
                v.travel_deal_fab.visibility = View.GONE
            }
        })
        val layoutManager = LinearLayoutManager(context)
        v.deals_recycler_view.layoutManager = layoutManager
        v.deals_recycler_view.adapter = travelDealsFirestoreAdapter
        v.deals_recycler_view.addItemDecoration(DividerItemDecoration(context, layoutManager.orientation))
        setHasOptionsMenu(true)
        return v
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_logout) {
            logOut(context!!)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

}
