package com.example.nxappc.domain.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nxappc.domain.database.daos.CartProductDao
import com.example.nxappc.domain.database.daos.ProductDao
import com.example.nxappc.domain.database.daos.UserDao
import com.example.nxappc.domain.database.entities.CartProduct
import com.example.nxappc.domain.database.entities.Product
import com.example.nxappc.domain.database.entities.User

@Database(entities = [CartProduct::class, Product::class, User::class], version = 1)
abstract class ShopDatabase : RoomDatabase() {
    abstract fun cartProductDao(): CartProductDao
    abstract fun productDao(): ProductDao
    abstract fun userDao(): UserDao
}