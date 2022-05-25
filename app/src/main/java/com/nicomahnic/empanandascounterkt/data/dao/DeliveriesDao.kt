package com.nicomahnic.empanandascounterkt.data.dao

import androidx.room.*
import com.nicomahnic.empanandascounterkt.models.room.DeliveryEntity

@Dao
interface DeliveriesDao {

    @Query("SELECT * FROM Delivery")
    suspend fun getAll(): List<DeliveryEntity>

    @Update
    suspend fun update(user: DeliveryEntity)

    @Delete
    suspend fun delete(user: DeliveryEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: DeliveryEntity) : Long

}