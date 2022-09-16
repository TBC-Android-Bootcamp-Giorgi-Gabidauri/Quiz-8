package com.gabo.quiz8.di

import android.app.Application
import androidx.room.Insert
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gabo.quiz8.constants.DATABASE_NAME
import com.gabo.quiz8.data.local.dao.PurchaseDao
import com.gabo.quiz8.data.local.database.PurchaseItemsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalStorageModule {

    @Provides
    @Singleton
    fun provideDataBase(application: Application): PurchaseItemsDatabase {
        return Room.databaseBuilder(application, PurchaseItemsDatabase::class.java, DATABASE_NAME)
            .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(dataBase: PurchaseItemsDatabase): PurchaseDao {
        return dataBase.getMoviesDao
    }
}