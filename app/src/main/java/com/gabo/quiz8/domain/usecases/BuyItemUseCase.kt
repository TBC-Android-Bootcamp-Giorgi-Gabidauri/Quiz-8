package com.gabo.quiz8.domain.usecases

import com.gabo.quiz8.base.BaseUseCase
import com.gabo.quiz8.domain.repository.PurchaseRepository
import javax.inject.Inject

class BuyItemUseCase@Inject constructor(private val repository: PurchaseRepository):
    BaseUseCase<Pair<Boolean,String>,Unit> {
    override suspend fun invoke(params: Pair<Boolean,String>) {
        repository.updateBoughtState(params.first,params.second)
    }
}