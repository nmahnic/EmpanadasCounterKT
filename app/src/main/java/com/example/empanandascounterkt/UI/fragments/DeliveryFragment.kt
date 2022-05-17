package com.example.empanandascounterkt.UI.fragments

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.empanandascounterkt.R
import com.example.empanandascounterkt.UI.viewmodels.DeliveryVM
import com.example.empanandascounterkt.adapters.deliveries.DeliveriesAdapter
import com.example.empanandascounterkt.databinding.FragmentDeliveryBinding
import com.example.empanandascounterkt.models.domain.Delivery
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeliveryFragment : Fragment(R.layout.fragment_delivery) {

    companion object {
        val deliveryList = mutableListOf(
            Delivery("Il nonno Pibe", "Avellaneda 96, C1405, CABA", "+54 9 11 6708-9981"),
            Delivery("Gefahr", "Avellaneda 201, C1405, CABA", "+54 9 11 6708-9981"),
        )
    }

    private val viewModel: DeliveryVM by viewModels()
    private lateinit var binding : FragmentDeliveryBinding
    private lateinit var adapter: DeliveriesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDeliveryBinding.bind(view)

        initRecyclerView()

        binding.floatingActionButton.setOnClickListener( onFloatingClicked )

    }

    private fun initRecyclerView(){
        val recyclerView = binding.rvDeliveries
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter  = DeliveriesAdapter(deliveryList, onItemSelected)
        recyclerView.adapter = adapter
    }

    private val onItemSelected = object :  DeliveriesAdapter.ItemListener {
        override fun onBtnClick(delivery: Delivery, position: Int) {
            Log.d("NM", "delivery => ${delivery}")
            paymentMethodDeliveryDialog(delivery)
        }
    }

    private val onFloatingClicked = object : View.OnClickListener{
        override fun onClick(p0: View?) {
            Log.d("NM", "delivery => onFloatingClicked")
        }
    }

    fun paymentMethodDeliveryDialog(delivery: Delivery){
        MaterialAlertDialogBuilder(
            requireContext(),
            com.google.android.material.R.style.MaterialAlertDialog_Material3
        )
            .setTitle("Indique metodo de Pago")
            .setView(R.layout.delivery_dialog)
            .setPositiveButton("Aceptar") { dialog, _ ->
                val d = dialog as Dialog
                val edtPaymentMethod = d.findViewById<TextInputLayout>(R.id.edtPaymentMethod)
                edtPaymentMethod.editText?.let { edtPaymentMethod ->
                    val paymentMethod = edtPaymentMethod.text.toString().trim()
                    if (paymentMethod.isNotEmpty()) {
                        sendWhatsappMessage(delivery, paymentMethod)
                    }
                }
                dialog.dismiss()
            }
            .show()
    }

    private fun sendWhatsappMessage(delivery: Delivery, paymentMethod: String){
        try {
            val message = viewModel.createMessage(delivery, paymentMethod)
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("http://api.whatsapp.com/send?phone=+54 9 ${delivery.whatsappNumber}&text=$message")
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}