package com.example.empanandascounterkt.data.mappers

import com.example.empanandascounterkt.data.EntityMapper
import com.example.empanandascounterkt.models.domain.Empanada
import com.example.empanandascounterkt.models.room.EmpanadaEntity
import javax.inject.Inject

class EmpanadasMapper @Inject constructor() : EntityMapper<EmpanadaEntity, Empanada> {

    override fun mapFromEntity(entity: EmpanadaEntity): Empanada {
        return Empanada(
            name = entity.empanada,
            quantity = entity.quantity,
            orderId = entity.orderId
        )
    }

    override fun mapToEntity(domainModel: Empanada?): EmpanadaEntity {
        return EmpanadaEntity(
            empanada = domainModel!!.name,
            quantity = domainModel.quantity,
            orderId = domainModel.orderId
        )
    }

}