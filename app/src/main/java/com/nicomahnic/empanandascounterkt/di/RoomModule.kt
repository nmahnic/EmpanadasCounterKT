package com.nicomahnic.empanandascounterkt.di

import android.content.Context
import androidx.room.Room
import com.nicomahnic.empanandascounterkt.data.AppDatabase
import com.nicomahnic.empanandascounterkt.data.dao.DeliveriesDao
import com.nicomahnic.empanandascounterkt.data.dao.EmpanadasDao
import com.nicomahnic.empanandascounterkt.data.dao.OrdersDao
import com.nicomahnic.empanandascounterkt.data.dao.UsersDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun providesAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideEmpanadasDAO(appDatabase: AppDatabase): EmpanadasDao {
        return appDatabase.empanadasDao()
    }

    @Singleton
    @Provides
    fun provideOrdersDAO(appDatabase: AppDatabase): OrdersDao {
        return appDatabase.orderDao()
    }

    @Singleton
    @Provides
    fun provideUsersDAO(appDatabase: AppDatabase): UsersDao {
        return appDatabase.usersDao()
    }

    @Singleton
    @Provides
    fun provideDeliveriesDAO(appDatabase: AppDatabase): DeliveriesDao {
        return appDatabase.deliveriesDao()
    }


}