package com.nicomahnic.empanandascounterkt.models.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Delivery")
data class DeliveryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val address: String,
    val city: String,
    val postCode: String,
    val date: Long,
    val whatsappNumber: String
)