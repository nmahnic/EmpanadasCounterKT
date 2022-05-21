package com.nicomahnic.empanandascounterkt.models.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Empanadas")
data class EmpanadaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val empanada: String,
    val quantity: Int,
    val orderId: Int
)