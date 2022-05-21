package com.nicomahnic.empanandascounterkt.UI.viewmodels

import androidx.lifecycle.ViewModel
import com.nicomahnic.empanandascounterkt.data.repositories.OrdersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrderVM @Inject constructor(
    private val orderRepo: OrdersRepository
): ViewModel() {

    var floatingButtonStatus: Boolean = false


    suspend fun getAllOrders() = orderRepo.getAllOrders()

}