package com.example.empanandascounterkt.data.repositories

import com.example.empanandascounterkt.data.dao.UsersDao
import com.example.empanandascounterkt.data.mappers.UserMapper
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class UsersRepository @Inject constructor(
    private val usersDao: UsersDao,
    private val mapper: UserMapper,
) {

    suspend fun getAllUsers() = flow {
        val userEntity = usersDao.getAll()
        emit( mapper.mapFromEntityList( userEntity ))
    }

}