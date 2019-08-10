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

class TravelDealsListFragment : Fragment() {

    private lateinit var travelDealsFirestoreAdapter: TravelDealsFirestoreAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (FirebaseAuth.getInstance().currentUser != null) {
            travelDealsFirestoreAdapter = TravelDealsFirestoreAdapter(getTravelDeals(query))
            lifecycle.addObserver(FirestoreAdapterState(travelDealsFirestoreAdapter))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_travel_deals_list, container, false)

        rootView.travel_deal_fab.setOnClickListener { view ->
            view.findNavController()
                .navigate(R.id.action_travelDestinationsFragment_to_addTravelDestinationFragment)
        }

        FirebaseRoles.isAdmin
            .observe(this, Observer { isAdmin ->
                rootView.travel_deal_fab.visibility = if (isAdmin) View.VISIBLE else View.GONE
            })

        val layoutManager = LinearLayoutManager(context)
        rootView.deals_recycler_view.layoutManager = layoutManager
        rootView.deals_recycler_view.adapter = travelDealsFirestoreAdapter
        rootView.deals_recycler_view.addItemDecoration(DividerItemDecoration(context, layoutManager.orientation))

        setHasOptionsMenu(true)

        return rootView
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_logout)
            logOut(context!!)

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

}
