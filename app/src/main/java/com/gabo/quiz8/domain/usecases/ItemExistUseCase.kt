package com.gabo.quiz8.domain.usecases

import com.gabo.quiz8.base.BaseUseCase
import com.gabo.quiz8.domain.repository.PurchaseRepository
import javax.inject.Inject

class ItemExistUseCase@Inject constructor(private val repository: PurchaseRepository): BaseUseCase<String,Boolean> {
    override suspend fun invoke(params: String): Boolean {
        return repository.exist(params)
    }
}