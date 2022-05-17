package com.example.empanandascounterkt.data.repositories

import android.util.Log
import com.example.empanandascounterkt.data.dao.EmpanadasDao
import com.example.empanandascounterkt.data.dao.OrdersDao
import com.example.empanandascounterkt.models.domain.Empanada
import com.example.empanandascounterkt.models.room.EmpanadaEntity
import com.example.empanandascounterkt.models.room.OrderEntity
import java.util.*
import javax.inject.Inject


class EmpanadasRepository @Inject constructor(
    private val empanadasDao: EmpanadasDao,
    private val ordersDao: OrdersDao,
) {

    suspend fun insertList(empanadas: List<Empanada>, comment: String){
        val orderId = ordersDao.insert(OrderEntity(date = Date().time, comment = comment))
        Log.d("NM", "order -> $orderId")
        empanadas.forEach{ empanada ->
            val empanadaEntity = EmpanadaEntity(
                empanada = empanada.name,
                quantity = empanada.quantity,
                orderId = orderId.toInt()
            )
            empanadasDao.insert(empanadaEntity)
        }
    }

}