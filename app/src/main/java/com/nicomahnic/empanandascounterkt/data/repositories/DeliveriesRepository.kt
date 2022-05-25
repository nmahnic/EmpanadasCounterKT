package com.nicomahnic.empanandascounterkt.data.repositories

import com.nicomahnic.empanandascounterkt.data.dao.DeliveriesDao
import com.nicomahnic.empanandascounterkt.data.mappers.DeliveriesMapper
import com.nicomahnic.empanandascounterkt.models.domain.Delivery
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeliveriesRepository @Inject constructor(
    private val deliveriesDao: DeliveriesDao,
    private val mapper: DeliveriesMapper,
) {

    suspend fun getAllDeliveries() = flow {
        val deliveryEntity = deliveriesDao.getAll()
        emit( mapper.mapFromEntityList( deliveryEntity ))
    }

    suspend fun insertDelivery(delivery: Delivery){
        deliveriesDao.insert( mapper.mapToEntity(delivery) )
    }

}