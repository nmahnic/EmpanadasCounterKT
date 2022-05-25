package com.nicomahnic.empanandascounterkt.ui.fragments

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.nicomahnic.empanandascounterkt.R
import com.nicomahnic.empanandascounterkt.ui.viewmodels.DeliveryVM
import com.nicomahnic.empanandascounterkt.adapters.deliveries.DeliveriesAdapter
import com.nicomahnic.empanandascounterkt.databinding.FragmentDeliveryBinding
import com.nicomahnic.empanandascounterkt.models.domain.Delivery
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputLayout
import com.nicomahnic.empanandascounterkt.models.domain.Order
import com.nicomahnic.empanandascounterkt.ui.dialog.AddDeliveryDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DeliveryFragment : Fragment(R.layout.fragment_delivery) {

    private val deliveryList = mutableListOf<Delivery>()
    private val deliveryVM: DeliveryVM by viewModels()
    private lateinit var binding : FragmentDeliveryBinding
    private lateinit var adapter: DeliveriesAdapter
    private val args: DeliveryFragmentArgs by navArgs()

    private var order: Order? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDeliveryBinding.bind(view)

        args.order?.let {
            Log.d("NM", "DELIVERY => NAV ARGS $it")
            order = it
        } ?: run {
            Log.d("NM", "DELIVERY => NO NAV ARGS")
        }

        initRecyclerView()

        binding.floatingActionButton.setOnClickListener( onFloatingClicked )

    }

    private fun initRecyclerView(){
        binding.rvDeliveries.layoutManager = LinearLayoutManager(requireContext())
        adapter  = DeliveriesAdapter(deliveryList, onItemSelected)
        binding.rvDeliveries.adapter = adapter

        CoroutineScope(Dispatchers.Main).launch{
            deliveryVM.getAllDeliveries().collect { deliveries ->
                deliveries.forEach {
                    Log.d("NM", "delivery => ${it} ")
                    deliveryList.add(0, it)
                    adapter.notifyItemChanged(0)
                }
            }
        }
    }

    private val onItemSelected = object :  DeliveriesAdapter.ItemListener {
        override fun onBtnClick(delivery: Delivery, position: Int) {
            Log.d("NM", "delivery => ${delivery}")
            order?.let {
                paymentMethodDeliveryDialog(delivery, it)
            }
        }
    }

    private val onFloatingClicked = object : View.OnClickListener{
        override fun onClick(p0: View?) { addNewDeliveryDialog() }
    }

    private fun addNewDeliveryDialog() {
        val dialog = AddDeliveryDialog(deliveryVM, deliveryDialogListener)
        val fm: FragmentManager = requireActivity().supportFragmentManager
        dialog.show(fm,"custom")
    }

    private val deliveryDialogListener = object : AddDeliveryDialog.DeliveryDialogListener{
        override fun addDelivery(delivery: Delivery) {
            deliveryList.add(delivery)
            adapter.notifyItemInserted(deliveryList.size - 1)
        }
    }

    private fun paymentMethodDeliveryDialog(delivery: Delivery, order: Order){
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
                    var paymentMethod = edtPaymentMethod.text.toString().trim()
                    if (paymentMethod.isEmpty()) { paymentMethod = "Efectivo"}
                    sendWhatsappMessage(delivery, paymentMethod, order)
                }
                dialog.dismiss()
            }
            .show()
    }

    private fun sendWhatsappMessage(delivery: Delivery, paymentMethod: String, order: Order){
        try {
            val message = deliveryVM.createMessage(paymentMethod, order)
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("http://api.whatsapp.com/send?phone=${delivery.whatsappNumber}&text=$message")
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}