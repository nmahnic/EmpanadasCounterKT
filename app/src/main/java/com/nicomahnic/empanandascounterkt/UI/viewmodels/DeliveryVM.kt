package com.nicomahnic.empanandascounterkt.UI.viewmodels

import androidx.lifecycle.ViewModel
import com.nicomahnic.empanandascounterkt.models.domain.Delivery
import com.nicomahnic.empanandascounterkt.models.domain.Order

class DeliveryVM: ViewModel() {

    fun createMessage(paymentMethod: String, order: Order) : String {

        var text = "Buenas noches queria hacerte un pedido para *Aranguren 168*\n"
        order.empanadaList.forEach {
            text += "${it.quantity}x ${it.name}\n"
        }
        text += "Pago con ${paymentMethod}, gracias!\n"

        return text
    }

}