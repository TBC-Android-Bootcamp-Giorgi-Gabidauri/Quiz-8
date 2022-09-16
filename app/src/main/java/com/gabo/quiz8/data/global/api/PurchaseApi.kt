package com.gabo.quiz8.data.global.api

import com.gabo.quiz8.constants.PURCHASE_END_POINT
import com.gabo.quiz8.data.global.dto.PurchaseItemDto
import retrofit2.Response
import retrofit2.http.GET

interface PurchaseApi {
    @GET(PURCHASE_END_POINT)
    suspend fun getPurchaseItems(): Response<List<PurchaseItemDto>>
}