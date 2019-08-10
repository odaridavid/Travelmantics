package com.blaccoder.travelmantics.ui.deals

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.blaccoder.travelmantics.R
import com.blaccoder.travelmantics.model.TravelDealTimestamped
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import timber.log.Timber


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
        Timber.d("$data")
        viewholder.bind(data)
    }

    inner class TravelDealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(timestamped: TravelDealTimestamped) {
            val dest = itemView.findViewById<TextView>(R.id.destination_title_text_view)
            val price = itemView.findViewById<TextView>(R.id.destination_price_text_view)
            dest.text = timestamped.title
            price.text = timestamped.price
        }
    }
}