package com.nicomahnic.empanandascounterkt.data.mappers

import com.nicomahnic.empanandascounterkt.data.EntityMapper
import com.nicomahnic.empanandascounterkt.models.domain.User
import com.nicomahnic.empanandascounterkt.models.room.UserEntity
import java.util.*
import javax.inject.Inject

class UserMapper @Inject constructor() : EntityMapper<UserEntity, User> {

    override fun mapFromEntity(entity: UserEntity): User {
        return User(
            id = entity.id,
            name = entity.name,
            address = entity.address,
            date = Date(entity.date),
        )
    }

    override fun mapToEntity(domainModel: User?): UserEntity {
        return UserEntity(
            name = domainModel!!.name,
            address = domainModel.address,
            date = domainModel.date.time
        )
    }

}