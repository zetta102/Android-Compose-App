package com.example.nxappc.domain.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.nxappc.domain.database.entities.Product

@Dao
interface ProductDao {
    @Insert
    fun insertProducts(products: List<Product>): List<Long>

    @Query("SELECT * FROM products WHERE id = :productId")
    fun getProduct(productId: Int): Product

    @Query("SELECT * FROM products")
    fun getAllProducts(): List<Product>

    @Query("SELECT * FROM products WHERE id IN (:productIds)")
    fun getProducts(productIds: List<Int>): List<Product>
}