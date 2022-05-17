package com.example.empanandascounterkt.di

import android.content.Context
import androidx.room.Room
import com.example.empanandascounterkt.data.AppDatabase
import com.example.empanandascounterkt.data.dao.EmpanadasDao
import com.example.empanandascounterkt.data.dao.OrdersDao
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


}