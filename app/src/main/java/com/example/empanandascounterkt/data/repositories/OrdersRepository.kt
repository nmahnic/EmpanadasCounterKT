package com.example.empanandascounterkt.data.repositories

import android.util.Log
import com.example.empanandascounterkt.data.dao.EmpanadasDao
import com.example.empanandascounterkt.data.dao.OrdersDao
import com.example.empanandascounterkt.data.mappers.EmpanadasMapper
import com.example.empanandascounterkt.data.mappers.OrderMapper
import com.example.empanandascounterkt.models.domain.Empanada
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class OrdersRepository @Inject constructor(
    private val empanadasDao: EmpanadasDao,
    private val ordersDao: OrdersDao,
    private val orderMapper: OrderMapper,
    private val empanadasMapper: EmpanadasMapper,
) {

    suspend fun getAllOrders() = flow {
        val entityOrders = ordersDao.getAll()
        val entityEmpanadas = empanadasDao.getAll()
        Log.d("NM", "OrderRepository -> $entityOrders")
        Log.d("NM", "OrderRepository -> $entityEmpanadas")
        val orders = orderMapper.mapFromEntityList( ordersDao.getAll() )
        val empanadas = empanadasMapper.mapFromEntityList( empanadasDao.getAll() )
        orders.forEach { order ->
            order.empanadaList = empanadas.filter { order.id == it.orderId } as MutableList<Empanada>
        }
        emit(orders)
    }

}