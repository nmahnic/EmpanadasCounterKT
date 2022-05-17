package com.example.empanandascounterkt.data.dao

import androidx.room.*
import com.example.empanandascounterkt.models.room.EmpanadaEntity
import com.example.empanandascounterkt.models.room.OrderEntity

@Dao
interface OrdersDao {

    @Query("SELECT * FROM Orders")
    suspend fun getAll(): List<OrderEntity>

    @Update
    suspend fun update(empanada: OrderEntity)

    @Delete
    suspend fun delete(empanada: OrderEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(order: OrderEntity) : Long

}