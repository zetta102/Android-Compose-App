package com.example.nxappc.ui.state

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.nxappc.ui.data.loadData
import com.example.nxappc.ui.objects.Product

class ProductModel : ViewModel() {
    var productList = loadData()

    val shoppingCartProductList: SnapshotStateList<Product> = mutableStateListOf()

    fun productDetailData(id: String?): Product {
        return productList.single { p -> p.id.toString() == id }
    }

    fun addToCart(id: String?) {
        shoppingCartProductList.add(productDetailData(id))
    }
}