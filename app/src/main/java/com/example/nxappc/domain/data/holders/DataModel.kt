package com.example.nxappc.domain.data.holders

import com.example.nxappc.domain.data.loaders.loadProductList
import com.example.nxappc.domain.data.loaders.loadUserData
import com.example.nxappc.domain.database.ShopDatabase
import com.example.nxappc.domain.database.entities.CartProduct
import com.example.nxappc.domain.database.entities.Product
import com.example.nxappc.domain.database.entities.User
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlin.math.roundToInt

class DataModel(db: ShopDatabase) {

    private val client = HttpClient(Android) {
        install(JsonFeature) {
            serializer = GsonSerializer {
                generateNonExecutableJson()
            }
        }
    }

    private val userDao = db.userDao()

    private val productDao = db.productDao()

    private val cartProductDao = db.cartProductDao()

    fun preloadProducts() {
        userDao.insertUser(runBlocking(Dispatchers.IO) { loadUserData(client) })
        productDao.insertProducts(runBlocking(Dispatchers.IO) { loadProductList(client) })
    }

    fun productList(): List<Product> {
        return productDao.getAllProducts()
    }

    fun cartProductList(): List<Product> {
        return productDao.getProducts(cartProductDao.getProducts().map { it.productId })
    }

    fun userData(): User {
        return userDao.getUser()
    }

    fun productDetailData(id: String?): Product {
        return productDao.getProduct(id!!.toInt())
    }

    fun addToCart(id: String?, quantity: Int) {
        if (id?.toInt()?.let { cartProductDao.getProduct(it) } != null) {
            cartProductDao.addOrRemoveFromCart(
                CartProduct(id.toInt(), quantity)
            )
        } else {
            cartProductDao.insertProduct(
                CartProduct(
                    id!!.toInt(), quantity
                )
            )
        }
    }

    fun addOrRemoveFromCart(id: String?, quantity: Int) {
        cartProductDao.addOrRemoveFromCart(CartProduct(id!!.toInt(), quantity))
    }

    fun getCost(): Int {
        var cost = 0.0
        cartProductDao.getTotalCost().forEach{
            cost += it.price * it.quantity
        }
        return cost.roundToInt()
    }
}