package com.gabo.quiz8.base

interface BaseUseCase<Params, Result> {
    suspend operator fun invoke(params: Params): Result
}