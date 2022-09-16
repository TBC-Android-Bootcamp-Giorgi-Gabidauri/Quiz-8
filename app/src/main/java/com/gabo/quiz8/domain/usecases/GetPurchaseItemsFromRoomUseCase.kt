package com.gabo.quiz8.domain.usecases

import com.gabo.quiz8.base.BaseUseCase
import com.gabo.quiz8.domain.models.PurchaseItemModel
import com.gabo.quiz8.domain.repository.PurchaseRepository
import javax.inject.Inject

class GetPurchaseItemsFromRoomUseCase @Inject constructor(private val repository: PurchaseRepository) :
    BaseUseCase<Unit, List<PurchaseItemModel>> {
    override suspend fun invoke(params: Unit): List<PurchaseItemModel> {
        return repository.getPurchaseItemsFromRoom()
    }
}