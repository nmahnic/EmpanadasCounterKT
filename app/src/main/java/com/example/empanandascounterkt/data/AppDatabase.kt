package com.example.empanandascounterkt.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.empanandascounterkt.data.dao.EmpanadasDao
import com.example.empanandascounterkt.data.dao.OrdersDao
import com.example.empanandascounterkt.models.room.EmpanadaEntity
import com.example.empanandascounterkt.models.room.OrderEntity

@Database( entities = [EmpanadaEntity::class, OrderEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object{
        const val DATABASE_NAME: String = "empanadas_counter_db"
    }

    abstract fun empanadasDao(): EmpanadasDao
    abstract fun orderDao(): OrdersDao
}