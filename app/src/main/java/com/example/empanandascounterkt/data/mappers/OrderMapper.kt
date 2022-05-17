package com.example.empanandascounterkt.data.mappers

import com.example.empanandascounterkt.models.domain.Order
import com.example.empanandascounterkt.models.domain.User
import com.example.empanandascounterkt.models.room.OrderEntity
import java.util.*
import javax.inject.Inject

class OrderMapper @Inject constructor() : EntityMapper<OrderEntity, Order> {

    override fun mapFromEntity(entity: OrderEntity): Order {
        return Order(
            id = entity.id,
            user = User(entity.name),
            date = Date(entity.date),
            comment = entity.comment,
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