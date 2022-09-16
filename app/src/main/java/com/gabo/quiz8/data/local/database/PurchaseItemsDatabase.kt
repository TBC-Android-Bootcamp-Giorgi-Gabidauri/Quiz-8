package com.gabo.quiz8.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gabo.quiz8.data.local.dao.PurchaseDao
import com.gabo.quiz8.data.local.dto.PurchaseItemDto

@Database(entities = [PurchaseItemDto::class], version = 4)
abstract class PurchaseItemsDatabase : RoomDatabase() {
    abstract val getMoviesDao: PurchaseDao
}