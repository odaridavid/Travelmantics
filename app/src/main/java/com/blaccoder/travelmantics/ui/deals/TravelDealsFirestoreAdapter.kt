package com.blaccoder.travelmantics.ui.deals

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.blaccoder.travelmantics.R
import com.blaccoder.travelmantics.model.TravelDealTimestamped
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions


/**
 * Created By David Odari
 * On 10/08/19
 *
 **/
class TravelDealsFirestoreAdapter(options: FirestoreRecyclerOptions<TravelDealTimestamped>) :
    FirestoreRecyclerAdapter<TravelDealTimestamped, TravelDealsFirestoreAdapter.TravelDealViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TravelDealViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.travel_deal_list_layout, parent, false)
        return TravelDealViewHolder(view)
    }

    override fun onBindViewHolder(viewholder: TravelDealViewHolder, position: Int, data: TravelDealTimestamped) {
        viewholder.bind(data)
    }

    inner class TravelDealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            view.findNavController()
                .navigate(TravelDealsListFragmentDirections.ListFragmentToDetailFragment(getItem(adapterPosition)))
        }

        fun bind(travelDealTimestamped: TravelDealTimestamped) {
            val destinationName = itemView.findViewById<TextView>(R.id.destination_title_text_view)
            val destinationPrice = itemView.findViewById<TextView>(R.id.destination_price_text_view)
            destinationName.text = travelDealTimestamped.title
            destinationPrice.text = travelDealTimestamped.price
        }
    }
}