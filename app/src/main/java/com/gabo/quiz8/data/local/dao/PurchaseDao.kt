package com.gabo.quiz8.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gabo.quiz8.constants.TABLE_NAME
import com.gabo.quiz8.data.local.dto.PurchaseItemDto

@Dao
interface PurchaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveItem(item: PurchaseItemDto)

    @Query("DELETE FROM $TABLE_NAME WHERE id=:id")
    suspend fun deleteItem(id: Int)

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun getItems(): List<PurchaseItemDto>

    @Query("SELECT EXISTS(SELECT*FROM $TABLE_NAME WHERE id=:id)")
    suspend fun itemExists(id: Int): Boolean
}