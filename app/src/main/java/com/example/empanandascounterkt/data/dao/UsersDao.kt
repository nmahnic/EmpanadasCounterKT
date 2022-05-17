package com.example.empanandascounterkt.data.dao

import androidx.room.*
import com.example.empanandascounterkt.models.room.EmpanadaEntity
import com.example.empanandascounterkt.models.room.OrderEntity
import com.example.empanandascounterkt.models.room.UserEntity

@Dao
interface UsersDao {

    @Query("SELECT * FROM Users")
    suspend fun getAll(): List<UserEntity>

    @Update
    suspend fun update(user: UserEntity)

    @Delete
    suspend fun delete(user: UserEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: UserEntity) : Long

}