package com.example.empanandascounterkt.data.repositories

import android.util.Log
import com.example.empanandascounterkt.data.dao.EmpanadasDao
import com.example.empanandascounterkt.data.dao.OrdersDao
import com.example.empanandascounterkt.data.mappers.OrderMapper
import com.example.empanandascounterkt.models.domain.Empanada
import com.example.empanandascounterkt.models.room.EmpanadaEntity
import com.example.empanandascounterkt.models.room.OrderEntity
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject


class OrdersRepository @Inject constructor(
    private val ordersDao: OrdersDao,
    private val mapper: OrderMapper,
) {

    suspend fun getAllOrders() = flow {
        val orders = ordersDao.getAll()
        Log.d("NM", "OrderRepository -> $orders")
        emit(mapper.mapFromEntityList( ordersDao.getAll() ))
    }

}