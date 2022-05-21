package com.nicomahnic.empanandascounterkt.data.mappers

import com.nicomahnic.empanandascounterkt.data.EntityMapper
import com.nicomahnic.empanandascounterkt.models.domain.Empanada
import com.nicomahnic.empanandascounterkt.models.room.EmpanadaEntity
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