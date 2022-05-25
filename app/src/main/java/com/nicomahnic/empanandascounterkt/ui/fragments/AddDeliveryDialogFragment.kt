package com.nicomahnic.empanandascounterkt.ui.fragments

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputLayout
import com.nicomahnic.empanandascounterkt.R
import com.nicomahnic.empanandascounterkt.databinding.AddDeliveryDialogBinding
import com.nicomahnic.empanandascounterkt.models.domain.Delivery
import com.nicomahnic.empanandascounterkt.ui.viewmodels.DeliveryVM

class AddDeliveryDialogFragment (
    private val deliveryVM: DeliveryVM,
    private val deliveryDialogListener: DeliveryDialogListener
): DialogFragment() {

    lateinit var binding: AddDeliveryDialogBinding

    interface DeliveryDialogListener{
        fun addDelivery(delivery: Delivery)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = AddDeliveryDialogBinding.inflate(LayoutInflater.from(requireContext()))

        var deliveryName = ""
        var deliveryWhatsappNum = ""
        var deliveryAddress = ""
        var deliveryCountryCode = ""

        return MaterialAlertDialogBuilder(requireContext())
            .setTitle("Delivery")
            .setPositiveButton("Aceptar") { dialog, _ ->
                val d = dialog as Dialog
                val tilDeliveryWhatsappNum = d.findViewById<TextInputLayout>(R.id.tilDeliveryWhatsappNum)
                val tilDeliveryName = d.findViewById<TextInputLayout>(R.id.tilDeliveryName)
                val tilDeliveryAddress = d.findViewById<TextInputLayout>(R.id.tilDeliveryAddress)
                val tilDeliveryCountryCode = d.findViewById<TextInputLayout>(R.id.tilDeliveryCountyCode)
                tilDeliveryName.editText?.let { edtDeliveryName ->
                    deliveryName = edtDeliveryName.text.toString()
                   Log.d("NM", "edtDeliveryName $deliveryName")
                }
                tilDeliveryWhatsappNum.editText?.let { edtDeliveryWhatsappNum ->
                    deliveryWhatsappNum = edtDeliveryWhatsappNum.text.toString()
                    Log.d("NM", "edtDeliveryWhatsappNum $deliveryWhatsappNum")
                }
                tilDeliveryAddress.editText?.let { edtDeliveryAddress ->
                    deliveryAddress = edtDeliveryAddress.text.toString()
                    Log.d("NM", "edtDeliveryWhatsappNum $deliveryWhatsappNum")
                }
                tilDeliveryCountryCode.editText?.let { edtDeliveryCountryCode ->
                    deliveryCountryCode = edtDeliveryCountryCode.text.toString()
                    Log.d("NM", "edtDeliveryWhatsappNum $deliveryWhatsappNum")
                }
                if( deliveryName.isNotEmpty() &&
                    deliveryWhatsappNum.isNotEmpty() &&
                    deliveryAddress.isNotEmpty() &&
                    deliveryCountryCode.isNotEmpty()
                ){
                    val delivery = Delivery(
                        name = deliveryName,
                        address = deliveryAddress,
                        city = "",
                        postCode = "",
                        whatsappNumber = "$deliveryCountryCode  9  $deliveryWhatsappNum"
                    )
                    deliveryVM.insertDelivery(delivery)
                    deliveryDialogListener.addDelivery(delivery)
                }
                dialog.dismiss()
            }
            .setView(binding.root)
            .create()
    }

}