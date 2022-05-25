package com.nicomahnic.empanandascounterkt.data.repositories

import android.util.Log
import com.nicomahnic.empanandascounterkt.data.dao.EmpanadasDao
import com.nicomahnic.empanandascounterkt.data.dao.OrdersDao
import com.nicomahnic.empanandascounterkt.data.dao.UsersDao
import com.nicomahnic.empanandascounterkt.data.mappers.EmpanadasMapper
import com.nicomahnic.empanandascounterkt.data.mappers.OrderMapper
import com.nicomahnic.empanandascounterkt.data.mappers.UserMapper
import com.nicomahnic.empanandascounterkt.models.domain.Empanada
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import java.text.SimpleDateFormat
import java.util.*
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
        coroutineScope {
            val first = Date().time

            val entityOrders = async { ordersDao.getAll() }
            val entityEmpanadas = async { empanadasDao.getAll() }
            val entityUsers = async { usersDao.getAll() }

            val orders = orderMapper.mapFromEntityList(entityOrders.await())
            val empanadas = empanadasMapper.mapFromEntityList(entityEmpanadas.await())
            val users = userMapper.mapFromEntityList(entityUsers.await())

            val time = Date().time - first
            val sdf = SimpleDateFormat("ss:SS")
            println("Time spent ${sdf.format(time)} ticks:${time}")

            orders.forEach { order ->
                order.empanadaList = empanadas.filter { order.id == it.orderId } as MutableList<Empanada>
                users.firstOrNull() { order.user.id == it.id }?.let { order.user = it }
            }
            emit(orders)
        }
    }

    suspend fun deleteOrder(id: Int){
        ordersDao.deleteById(id)
        empanadasDao.deleteByOrderId(id)
    }

}