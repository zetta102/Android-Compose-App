package com.example.nxappc.domain.data.loaders

import com.example.nxappc.domain.database.entities.Product
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

suspend fun loadProductList(client: HttpClient): ArrayList<Product> {
    lateinit var productList: ArrayList<Product>
    coroutineScope { launch { productList = client.get("https://fakestoreapi.com/products") } }
    return productList
}