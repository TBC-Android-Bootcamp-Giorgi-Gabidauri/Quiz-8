package com.gabo.quiz8.data.global.dto

import com.gabo.quiz8.domain.models.PurchaseItemModel

data class PurchaseItemDto(
    val title: String,
    val cover: String,
    val price: String,
    val liked: Boolean
) {
}

fun PurchaseItemDto.toModel() = PurchaseItemModel(
    title, cover, price, liked
)
