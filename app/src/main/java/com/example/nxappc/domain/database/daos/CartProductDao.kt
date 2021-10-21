package com.example.nxappc.domain.database.daos

import androidx.room.*
import com.example.nxappc.domain.database.entities.CartProduct
import com.example.nxappc.domain.database.entities.utils.CartCost

@Dao
interface CartProductDao {
    @Insert
    fun insertProduct(cartProduct: CartProduct): Long

    @Query("SELECT * FROM cart WHERE productId = :productId")
    fun getProduct(productId: Int): CartProduct

    @Query("SELECT * FROM cart")
    fun getProducts(): List<CartProduct>

//    @Query("UPDATE cart SET quantity = :quantity WHERE productId = :productId")
//    fun addOrRemoveFromCart(productId: Int, quantity: Int)

    @Update
    fun addOrRemoveFromCart(cartProduct: CartProduct)

    @Query("SELECT id, price, quantity FROM products INNER JOIN cart on id = productId")
    fun getTotalCost(): List<CartCost>
}