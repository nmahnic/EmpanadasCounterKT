package com.example.empanandascounterkt.UI.viewmodels

import androidx.lifecycle.ViewModel
import com.example.empanandascounterkt.models.domain.Delivery

class DeliveryVM: ViewModel() {

    fun createMessage(delivery: Delivery, paymentMethod: String) : String {
        var text = "Buenas noches queria hacerte un pedido para *Aranguren 168*\n"
        text += "2x Carne\n"
        text += "2x Jamon y queso\n"
        text += "2x Humita\n"
        text += "Pago con ${paymentMethod}, gracias!\n"

        return text
    }

}