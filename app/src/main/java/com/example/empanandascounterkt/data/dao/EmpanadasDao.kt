package com.example.empanandascounterkt.data.dao

import androidx.room.*
import com.example.empanandascounterkt.models.room.EmpanadaEntity

@Dao
interface EmpanadasDao {

    @Query("SELECT * FROM Empanadas")
    suspend fun getAll(): List<EmpanadaEntity>

    @Update
    suspend fun update(empanada: EmpanadaEntity)

    @Delete
    suspend fun delete(empanada: EmpanadaEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(empanada: EmpanadaEntity)

}