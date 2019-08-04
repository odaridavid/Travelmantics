package com.blaccoder.travelmantics.ui.details


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.blaccoder.travelmantics.R
import com.blaccoder.travelmantics.model.TravelDeal
import com.blaccoder.travelmantics.removeFromFirestore
import com.blaccoder.travelmantics.saveToFirestore
import com.blaccoder.travelmantics.ui.showLongMessage
import kotlinx.android.synthetic.main.fragment_travel_deal.view.*


class TravelDealFragment : Fragment() {

    lateinit var fragView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragView = inflater.inflate(R.layout.fragment_travel_deal, container, false)
        setHasOptionsMenu(true)
        return fragView
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                val title = fragView.destination_name_edit_text.text.toString()
                val description = fragView.destination_description_edit_text.text.toString()
                val price = fragView.price_edit_text.text.toString()
                saveToFirestore(TravelDeal(title, description, price, ""))
                showLongMessage(context!!, "Saved")
                clearFields()
                true
            }
            R.id.action_delete -> {
                removeFromFirestore(0)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun clearFields() {
        fragView.destination_description_edit_text.setText("")
        fragView.destination_name_edit_text.setText("")
        fragView.price_edit_text.setText("")
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.admin_travel_deals_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


}
