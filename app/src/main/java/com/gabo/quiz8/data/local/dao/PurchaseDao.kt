package com.gabo.quiz8.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gabo.quiz8.constants.TABLE_NAME
import com.gabo.quiz8.data.local.dto.PurchaseItemDto
import kotlinx.coroutines.flow.Flow

@Dao
interface PurchaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveItem(item: PurchaseItemDto)

    @Query("UPDATE $TABLE_NAME SET bought=:bought WHERE title=:title")
    suspend fun updateBoughtState(bought: Boolean, title: String)

    @Query("DELETE FROM $TABLE_NAME WHERE cover=:cover")
    suspend fun deleteItem(cover: String)

    @Query("SELECT * FROM $TABLE_NAME")
    fun getItems(): Flow<List<PurchaseItemDto>>

    @Query("SELECT EXISTS(SELECT*FROM $TABLE_NAME WHERE cover=:cover)")
    suspend fun itemExists(cover: String): Boolean

    @Query("SELECT * FROM $TABLE_NAME WHERE bought=1")
    fun getBoughtItems(): Flow<List<PurchaseItemDto>>
}