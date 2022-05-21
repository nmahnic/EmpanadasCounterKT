package com.nicomahnic.empanandascounterkt.data.mappers

import com.nicomahnic.empanandascounterkt.data.EntityMapper
import com.nicomahnic.empanandascounterkt.models.domain.Order
import com.nicomahnic.empanandascounterkt.models.domain.User
import com.nicomahnic.empanandascounterkt.models.room.OrderEntity
import java.util.*
import javax.inject.Inject

class OrderMapper @Inject constructor() : EntityMapper<OrderEntity, Order> {

    override fun mapFromEntity(entity: OrderEntity): Order {
        return Order(
            id = entity.id,
            user = User(entity.name),
            date = Date(entity.date),
            comment = entity.comment,
            selected = false,
        )
    }

    override fun mapToEntity(domainModel: Order?): OrderEntity {
        return OrderEntity(
            name = domainModel!!.user.name,
            comment = domainModel.comment,
            date = domainModel.date.time
        )
    }

}