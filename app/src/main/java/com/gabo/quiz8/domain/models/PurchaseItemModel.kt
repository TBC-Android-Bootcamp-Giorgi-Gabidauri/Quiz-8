package com.gabo.quiz8.domain.models

import com.gabo.quiz8.data.local.dto.PurchaseItemDto

data class PurchaseItemModel(
    val title: String,
    val cover: String,
    val price: String,
    val liked: Boolean,
    var bought: Boolean = false
)

fun PurchaseItemModel.toRoomDto() = PurchaseItemDto(
     title, cover, price, liked, bought
)