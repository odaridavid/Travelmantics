package com.blaccoder.travelmantics.ui.details


import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.blaccoder.travelmantics.FirebaseRoles
import com.blaccoder.travelmantics.R
import com.blaccoder.travelmantics.model.TravelDeal
import com.blaccoder.travelmantics.ui.ViewModelFactory
import com.blaccoder.travelmantics.ui.showLongMessage
import com.blaccoder.travelmantics.ui.showShortMessage
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_travel_deal.view.*


class TravelDealFragment : Fragment() {

    lateinit var rootView: View
    lateinit var viewModel: TravelDealViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        rootView = inflater.inflate(R.layout.fragment_travel_deal, container, false)

        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(FirebaseFirestore.getInstance())
        )[TravelDealViewModel::class.java]
//TODO Readarguments to know whether its in edit or view mode
        FirebaseRoles.isAdmin.observe(this, Observer { isAdmin ->
            if (!isAdmin) {
                rootView.destination_name_edit_text.isEnabled = false
                rootView.destination_name_edit_text.isFocusable = false
                rootView.destination_description_edit_text.isEnabled = false
                rootView.destination_description_edit_text.isFocusable = false
                rootView.price_edit_text.isEnabled = false
                rootView.price_edit_text.isFocusable = false
                rootView.select_image_button.visibility = View.GONE
            }
        })

        setHasOptionsMenu(true)

        return rootView
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                saveTravelDeal()
                true
            }
            R.id.action_delete -> {
                //TODO Init Travel Deal
                viewModel.removeFromFirestore("")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        FirebaseRoles.isAdmin.observe(this, Observer { isAdmin ->
            if (!isAdmin) {
                menu.findItem(R.id.action_delete).isVisible = false
                menu.findItem(R.id.action_save).isVisible = false
            }
        })
        super.onPrepareOptionsMenu(menu)
    }

    private fun saveTravelDeal() {
        val title = rootView.destination_name_edit_text.text.toString()
        val description = rootView.destination_description_edit_text.text.toString()
        val price = rootView.price_edit_text.text.toString()
        if (title.isBlank() && price.isBlank() && price.isBlank()) {
            showShortMessage(context!!, getString(R.string.message_empty_fields))
        } else {
            viewModel.saveToFirestore(TravelDeal(title, description, price, ""))
            showLongMessage(context!!, getString(R.string.message_saved))
            clearFields()
        }
    }

    private fun clearFields() {
        rootView.destination_description_edit_text.setText("")
        rootView.destination_name_edit_text.setText("")
        rootView.price_edit_text.setText("")
    }

    fun selectTravelDestinationImage() {
        TODO("Pick image from gallery")
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.admin_travel_deals_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        TODO("Register runtime permissions for reading external storage")
    }

}
