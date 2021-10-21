package com.example.nxappc.domain.database.entities.utils

import androidx.room.ColumnInfo

data class CartCost(
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "price") val price: Double,
    @ColumnInfo(name = "quantity") val quantity: Int
)
