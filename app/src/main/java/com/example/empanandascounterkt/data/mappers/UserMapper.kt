package com.example.empanandascounterkt.data.mappers

import com.example.empanandascounterkt.data.EntityMapper
import com.example.empanandascounterkt.models.domain.User
import com.example.empanandascounterkt.models.room.UserEntity
import java.util.*
import javax.inject.Inject

class UserMapper @Inject constructor() : EntityMapper<UserEntity, User> {

    override fun mapFromEntity(entity: UserEntity): User {
        return User(
            name = entity.name,
            date = Date(entity.date),
        )
    }

    override fun mapToEntity(domainModel: User?): UserEntity {
        return UserEntity(
            name = domainModel!!.name,
            date = domainModel.date.time
        )
    }

}