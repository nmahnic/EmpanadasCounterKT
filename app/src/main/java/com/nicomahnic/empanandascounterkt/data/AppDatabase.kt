package com.nicomahnic.empanandascounterkt.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nicomahnic.empanandascounterkt.data.dao.DeliveriesDao
import com.nicomahnic.empanandascounterkt.data.dao.EmpanadasDao
import com.nicomahnic.empanandascounterkt.data.dao.OrdersDao
import com.nicomahnic.empanandascounterkt.data.dao.UsersDao
import com.nicomahnic.empanandascounterkt.models.room.DeliveryEntity
import com.nicomahnic.empanandascounterkt.models.room.EmpanadaEntity
import com.nicomahnic.empanandascounterkt.models.room.OrderEntity
import com.nicomahnic.empanandascounterkt.models.room.UserEntity

@Database( entities = [
    EmpanadaEntity::class, OrderEntity::class,
    UserEntity::class, DeliveryEntity::class
], version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object{
        const val DATABASE_NAME: String = "empanadas_counter_db"
    }

    abstract fun empanadasDao(): EmpanadasDao
    abstract fun orderDao(): OrdersDao
    abstract fun usersDao(): UsersDao
    abstract fun deliveriesDao(): DeliveriesDao
}