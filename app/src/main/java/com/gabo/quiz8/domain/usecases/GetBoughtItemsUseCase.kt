package com.gabo.quiz8.domain.usecases

import com.gabo.quiz8.base.BaseUseCase
import com.gabo.quiz8.domain.models.PurchaseItemModel
import com.gabo.quiz8.domain.repository.PurchaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBoughtItemsUseCase @Inject constructor(private val repository: PurchaseRepository) :
    BaseUseCase<Unit, Flow<List<PurchaseItemModel>>> {
    override suspend fun invoke(params: Unit): Flow<List<PurchaseItemModel>> {
        return repository.getBoughtItems()
    }
}