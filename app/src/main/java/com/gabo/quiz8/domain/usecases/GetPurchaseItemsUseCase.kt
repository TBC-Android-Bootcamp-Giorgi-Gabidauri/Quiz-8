package com.gabo.quiz8.domain.usecases

import com.gabo.quiz8.base.BaseUseCase
import com.gabo.quiz8.common.ResponseHandler
import com.gabo.quiz8.domain.models.PurchaseItemModel
import com.gabo.quiz8.domain.repository.PurchaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPurchaseItemsUseCase @Inject constructor(private val repository: PurchaseRepository) :
    BaseUseCase<Unit, Flow<ResponseHandler<List<PurchaseItemModel>>>> {
    override suspend fun invoke(params: Unit): Flow<ResponseHandler<List<PurchaseItemModel>>> = flow{
        emit(repository.getPurchaseItems())
    }
}