package com.blaccoder.travelmantics.ui.details


import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.blaccoder.travelmantics.FirebaseRoles
import com.blaccoder.travelmantics.NAV_ARG_TRAVEL_DEAL
import com.blaccoder.travelmantics.R
import com.blaccoder.travelmantics.model.TravelDeal
import com.blaccoder.travelmantics.model.TravelDealTimestamped
import com.blaccoder.travelmantics.ui.ViewModelFactory
import com.blaccoder.travelmantics.ui.showLongMessage
import com.blaccoder.travelmantics.ui.showShortMessage
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_travel_deal.view.*


class TravelDealFragment : Fragment() {

    lateinit var rootView: View
    lateinit var viewModel: TravelDealViewModel
    private val args: TravelDealFragmentArgs by navArgs()
    private var isNewDeal = false
    var travelDealTimestamped: TravelDealTimestamped? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        travelDealTimestamped = arguments?.getParcelable<TravelDealTimestamped>(NAV_ARG_TRAVEL_DEAL)
        isNewDeal = travelDealTimestamped == null
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(FirebaseFirestore.getInstance())
        )[TravelDealViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        rootView = inflater.inflate(R.layout.fragment_travel_deal, container, false)

        FirebaseRoles.isAdmin.observe(this, Observer { isAdmin ->
            if (!isAdmin) {
                rootView.destination_name_edit_text.isEnabled = false
                rootView.destination_name_edit_text.isFocusable = false
                rootView.destination_description_edit_text.isEnabled = false
                rootView.destination_description_edit_text.isFocusable = false
                rootView.price_edit_text.isEnabled = false
                rootView.price_edit_text.isFocusable = false
                rootView.select_image_button.visibility = View.GONE
            } else {
                setHasOptionsMenu(true)
            }
        })
        if (travelDealTimestamped != null)
            setData(travelDealTimestamped)
        return rootView
    }

    private fun setData(travelDealTimestamped: TravelDealTimestamped?) {
        rootView.destination_name_edit_text.setText(travelDealTimestamped?.title)
        rootView.destination_description_edit_text.setText(travelDealTimestamped?.description)
        rootView.price_edit_text.setText(travelDealTimestamped?.price)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                if (isNewDeal)
                    saveTravelDeal()
                else
                    editTravelDeal()
                true
            }
            R.id.action_delete -> {
                if (isNewDeal) {
                    showLongMessage(context!!, getString(R.string.message_nothing_to_delete))
                } else {
                    viewModel.removeFromFirestore(travelDealTimestamped!!.id!!)
                    rootView.findNavController().navigate(R.id.BackToList)
                    showLongMessage(context!!, getString(R.string.message_deleted))
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun editTravelDeal() {
        val (title, description, price) = extractDetailsFromView()
        viewModel.updateToFirestore(TravelDealTimestamped(title, description, price), travelDealTimestamped?.id!!)
        showLongMessage(context!!, getString(R.string.message_edited))
    }

    private fun saveTravelDeal() {
        val (title, description, price) = extractDetailsFromView()
        if (title.isBlank() && price.isBlank() && price.isBlank()) {
            showShortMessage(context!!, getString(R.string.message_empty_fields))
        } else {
            viewModel.saveToFirestore(TravelDeal(title, description, price, ""))
            showLongMessage(context!!, getString(R.string.message_saved))
            clearFields()
        }
    }

    private fun extractDetailsFromView(): Triple<String, String, String> {
        val title = rootView.destination_name_edit_text.text.toString()
        val description = rootView.destination_description_edit_text.text.toString()
        val price = rootView.price_edit_text.text.toString()
        return Triple(title, description, price)
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
