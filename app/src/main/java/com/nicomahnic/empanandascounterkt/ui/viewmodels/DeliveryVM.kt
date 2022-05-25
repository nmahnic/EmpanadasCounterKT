package com.nicomahnic.empanandascounterkt.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicomahnic.empanandascounterkt.data.repositories.DeliveriesRepository
import com.nicomahnic.empanandascounterkt.models.domain.Delivery
import com.nicomahnic.empanandascounterkt.models.domain.Order
import com.nicomahnic.empanandascounterkt.models.domain.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeliveryVM @Inject constructor(
    private val deliveriesRepo: DeliveriesRepository
): ViewModel() {

    fun createMessage(paymentMethod: String, order: Order) : String {

        var text = "Buenas noches queria hacerte un pedido para *Aranguren 168*\n"
        order.empanadaList.forEach {
            text += "${it.quantity}x ${it.name}\n"
        }
        text += "Pago con ${paymentMethod}, gracias!\n"

        return text
    }

    suspend fun getAllDeliveries() = deliveriesRepo.getAllDeliveries()

    fun insertDelivery(delivery: Delivery){
        viewModelScope.launch {
            deliveriesRepo.insertDelivery(delivery)
        }
    }

}