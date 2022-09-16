package com.gabo.quiz8.domain.repository

import com.gabo.quiz8.common.ResponseHandler
import com.gabo.quiz8.data.local.dto.PurchaseItemDto
import com.gabo.quiz8.domain.models.PurchaseItemModel

interface PurchaseRepository {
    suspend fun getPurchaseItems(): ResponseHandler<List<PurchaseItemModel>>
    suspend fun getPurchaseItemsFromRoom(): List<PurchaseItemModel>
    suspend fun savePurchaseItems(list: List<PurchaseItemDto>)
}