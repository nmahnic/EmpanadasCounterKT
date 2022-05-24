package com.nicomahnic.empanandascounterkt.data.repositories

import com.nicomahnic.empanandascounterkt.data.dao.UsersDao
import com.nicomahnic.empanandascounterkt.data.mappers.UserMapper
import com.nicomahnic.empanandascounterkt.models.domain.User
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

    suspend fun insertUser(user: User){
        usersDao.insert( mapper.mapToEntity(user) )
    }

}