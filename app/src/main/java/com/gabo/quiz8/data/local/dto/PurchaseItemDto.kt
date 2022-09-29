package com.gabo.quiz8.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gabo.quiz8.constants.TABLE_NAME
import com.gabo.quiz8.domain.models.PurchaseItemModel

@Entity(tableName = TABLE_NAME)
data class PurchaseItemDto(
    @PrimaryKey(autoGenerate = false)
    val title: String,
    val cover: String,
    val price: String,
    val liked: Boolean,
    var bought: Boolean = false
)

fun PurchaseItemDto.toModel() = PurchaseItemModel(
    title, cover, price, liked, bought
)