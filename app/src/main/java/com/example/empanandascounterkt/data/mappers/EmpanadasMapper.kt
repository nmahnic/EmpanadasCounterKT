package com.example.empanandascounterkt.data.mappers

import com.example.empanandascounterkt.models.domain.Empanada
import com.example.empanandascounterkt.models.domain.Order
import com.example.empanandascounterkt.models.domain.User
import com.example.empanandascounterkt.models.room.EmpanadaEntity
import com.example.empanandascounterkt.models.room.OrderEntity
import java.util.*
import javax.inject.Inject

class EmpanadasMapper @Inject constructor() : EntityMapper<EmpanadaEntity, Empanada> {

    override fun mapFromEntity(entity: EmpanadaEntity): Empanada {
        return Empanada(
            name = entity.empanada,
            count = entity.quantity,
            orderId = entity.orderId
        )
    }

    override fun mapToEntity(domainModel: Empanada?): EmpanadaEntity {
        return EmpanadaEntity(
            empanada = domainModel!!.name,
            quantity = domainModel.count,
            orderId = domainModel.orderId
        )
    }

}