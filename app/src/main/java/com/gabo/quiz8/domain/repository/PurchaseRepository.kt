package com.gabo.quiz8.domain.repository

import com.gabo.quiz8.common.ResponseHandler
import com.gabo.quiz8.data.local.dto.PurchaseItemDto
import com.gabo.quiz8.domain.models.PurchaseItemModel
import kotlinx.coroutines.flow.Flow

interface PurchaseRepository {
    suspend fun getPurchaseItems(): ResponseHandler<List<PurchaseItemModel>>
    suspend fun getPurchaseItemsFromRoom(): Flow<List<PurchaseItemModel>>
    suspend fun savePurchaseItems(list: List<PurchaseItemDto>)
    suspend fun saveItem(item: PurchaseItemDto)
    suspend fun deleteItem(cover: String)
    suspend fun exist(cover: String): Boolean
    suspend fun getBoughtItems(): Flow<List<PurchaseItemModel>>
    suspend fun updateBoughtState(bought: Boolean, title: String)
}