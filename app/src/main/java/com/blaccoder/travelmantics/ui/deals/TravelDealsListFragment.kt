package com.blaccoder.travelmantics.ui.deals


import android.app.Activity
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.blaccoder.travelmantics.*
import com.blaccoder.travelmantics.services.FirebaseAuthState
import com.blaccoder.travelmantics.services.FirestoreAdapterState
import com.blaccoder.travelmantics.ui.ViewModelFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_travel_deals_list.view.*
import timber.log.Timber

class TravelDealsListFragment : Fragment() {

    private lateinit var travelDealsFirestoreAdapter: TravelDealsFirestoreAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (FirebaseAuth.getInstance().currentUser != null) {
            loadData()
        } else {
            login()
            loadData()
        }
    }

    private fun loadData() {
        travelDealsFirestoreAdapter = TravelDealsFirestoreAdapter(getTravelDeals(query))
        lifecycle.addObserver(FirestoreAdapterState(travelDealsFirestoreAdapter))
    }

    private fun login() {
        Timber.d("No User")
        val viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(FirebaseFirestore.getInstance())
        ).get(TravelDealsListViewModel::class.java)
        lifecycle.addObserver(FirebaseAuthState(activity as Activity, viewModel))
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
        rootView.deals_recycler_view.addItemDecoration(
            DividerItemDecoration(
                context,
                layoutManager.orientation
            )
        )

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
