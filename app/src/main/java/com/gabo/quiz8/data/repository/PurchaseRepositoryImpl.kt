package com.gabo.quiz8.data.repository

import com.gabo.quiz8.common.ResponseHandler
import com.gabo.quiz8.data.global.api.PurchaseApi
import com.gabo.quiz8.data.global.dto.toModel
import com.gabo.quiz8.data.local.database.PurchaseItemsDatabase
import com.gabo.quiz8.data.local.dto.PurchaseItemDto
import com.gabo.quiz8.data.local.dto.toModel
import com.gabo.quiz8.domain.models.PurchaseItemModel
import com.gabo.quiz8.domain.repository.PurchaseRepository
import javax.inject.Inject

class PurchaseRepositoryImpl @Inject constructor(
    private val purchaseApi: PurchaseApi,
    private val database: PurchaseItemsDatabase
) : PurchaseRepository {
    override suspend fun getPurchaseItems(): ResponseHandler<List<PurchaseItemModel>> {
        return try {
            val result = purchaseApi.getPurchaseItems()
            when {
                result.isSuccessful -> {
                    val body = result.body()
                    ResponseHandler.Success(body?.map { it.toModel() })
                }
                else -> {
                    val errorMsg = result.errorBody()?.string()
                    ResponseHandler.Error(errorMsg)
                }
            }
        } catch (e: Exception) {
            ResponseHandler.Error("Unexpected error occurred, check internet connection")
        }
    }

    override suspend fun getPurchaseItemsFromRoom(): List<PurchaseItemModel> {
        return database.getMoviesDao.getItems().map { it.toModel() }
    }

    override suspend fun savePurchaseItems(list: List<PurchaseItemDto>) {
        list.forEach { database.getMoviesDao.saveItem(it) }
    }


}