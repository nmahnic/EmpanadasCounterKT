package com.nicomahnic.empanandascounterkt.data.repositories

import android.util.Log
import com.nicomahnic.empanandascounterkt.data.dao.EmpanadasDao
import com.nicomahnic.empanandascounterkt.data.dao.OrdersDao
import com.nicomahnic.empanandascounterkt.data.mappers.EmpanadasMapper
import com.nicomahnic.empanandascounterkt.data.mappers.OrderMapper
import com.nicomahnic.empanandascounterkt.models.domain.Empanada
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

    suspend fun deleteOrder(id: Int){
        ordersDao.deleteById(id)
    }

}