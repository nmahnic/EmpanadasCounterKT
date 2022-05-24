package com.nicomahnic.empanandascounterkt.data.repositories

import android.util.Log
import com.nicomahnic.empanandascounterkt.data.dao.EmpanadasDao
import com.nicomahnic.empanandascounterkt.data.dao.OrdersDao
import com.nicomahnic.empanandascounterkt.data.dao.UsersDao
import com.nicomahnic.empanandascounterkt.data.mappers.EmpanadasMapper
import com.nicomahnic.empanandascounterkt.data.mappers.OrderMapper
import com.nicomahnic.empanandascounterkt.data.mappers.UserMapper
import com.nicomahnic.empanandascounterkt.models.domain.Empanada
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class OrdersRepository @Inject constructor(
    private val empanadasDao: EmpanadasDao,
    private val ordersDao: OrdersDao,
    private val usersDao: UsersDao,
    private val orderMapper: OrderMapper,
    private val empanadasMapper: EmpanadasMapper,
    private val userMapper: UserMapper,
) {

    suspend fun getAllOrders() = flow {
        val entityOrders = ordersDao.getAll()
        val entityEmpanadas = empanadasDao.getAll()
        val entityUsers = usersDao.getAll()
        Log.d("NM", "OrderRepository -> $entityOrders")
        Log.d("NM", "OrderRepository -> $entityEmpanadas")
        Log.d("NM", "OrderRepository -> $entityUsers")
        val orders = orderMapper.mapFromEntityList( ordersDao.getAll() )
        val empanadas = empanadasMapper.mapFromEntityList( empanadasDao.getAll() )
        val users = userMapper.mapFromEntityList( usersDao.getAll() )
        orders.forEach { order ->
            order.empanadaList = empanadas.filter { order.id == it.orderId } as MutableList<Empanada>
            users.firstOrNull() { order.user.id == it.id }?.let {
                order.user = it
            }
        }
        emit(orders)
    }

    suspend fun deleteOrder(id: Int){
        ordersDao.deleteById(id)
        empanadasDao.deleteByOrderId(id)
    }

}