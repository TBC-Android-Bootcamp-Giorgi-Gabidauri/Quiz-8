package com.gabo.quiz8.domain.usecases

import com.gabo.quiz8.base.BaseUseCase
import com.gabo.quiz8.data.local.dto.PurchaseItemDto
import com.gabo.quiz8.domain.repository.PurchaseRepository
import javax.inject.Inject

class SavePurchaseItemsUseCase@Inject constructor(private val repository: PurchaseRepository): BaseUseCase<List<PurchaseItemDto>,Unit> {
    override suspend fun invoke(params: List<PurchaseItemDto>) {
        repository.savePurchaseItems(params)
    }
}