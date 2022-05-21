package com.nicomahnic.empanandascounterkt.data.dao

import androidx.room.*
import com.nicomahnic.empanandascounterkt.models.room.EmpanadaEntity
import com.nicomahnic.empanandascounterkt.models.room.OrderEntity

@Dao
interface OrdersDao {

    @Query("SELECT * FROM Orders")
    suspend fun getAll(): List<OrderEntity>

    @Update
    suspend fun update(order: OrderEntity)

    @Delete
    suspend fun delete(order: OrderEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(order: OrderEntity) : Long

}