package com.nicomahnic.empanandascounterkt.data.dao

import androidx.room.*
import com.nicomahnic.empanandascounterkt.models.room.OrderEntity

@Dao
interface OrdersDao {

    @Query("SELECT * FROM Orders")
    suspend fun getAll(): List<OrderEntity>

    @Update
    suspend fun update(order: OrderEntity)

    @Delete
    suspend fun delete(order: OrderEntity)

    @Query("DELETE FROM Orders WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(order: OrderEntity) : Long

}