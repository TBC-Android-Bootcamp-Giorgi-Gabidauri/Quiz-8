package com.gabo.quiz8.domain.usecases

import com.gabo.quiz8.base.BaseUseCase
import com.gabo.quiz8.domain.repository.PurchaseRepository
import javax.inject.Inject

class DeleteItemUseCase@Inject constructor(private val repository: PurchaseRepository): BaseUseCase<String,Unit> {
    override suspend fun invoke(params: String) {
        repository.deleteItem(params)
    }
}