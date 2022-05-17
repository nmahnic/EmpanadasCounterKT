package com.example.empanandascounterkt.UI.viewmodels

import androidx.lifecycle.ViewModel
import com.example.empanandascounterkt.data.repositories.OrdersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrderVM @Inject constructor(
    private val orderRepo: OrdersRepository
): ViewModel() {

    var variable: Boolean = false


    suspend fun getAllOrders() = orderRepo.getAllOrders()

}