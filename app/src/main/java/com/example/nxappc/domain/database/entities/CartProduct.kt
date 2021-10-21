package com.example.nxappc.domain.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class CartProduct(
    @PrimaryKey
    val productId: Int,
    val quantity: Int
)
