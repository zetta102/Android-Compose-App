package com.example.nxappc.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.example.nxappc.R
import com.example.nxappc.ui.composables.BottomNavigationBar
import com.example.nxappc.ui.composables.CartCard
import com.example.nxappc.ui.composables.TopAppBar
import com.example.nxappc.ui.objects.Product
import com.example.nxappc.ui.state.ProductModel

@ExperimentalCoilApi
@Composable
fun ShoppingCart(productModel: ProductModel, navController: NavController) {
    val cartList = productModel.shoppingCartProductList
    Scaffold(
        topBar = { TopAppBar() },
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = it.calculateTopPadding() + 16.dp,
                    bottom = it.calculateBottomPadding() + 16.dp
                )
        ) {
            if (cartList.isNotEmpty()) {
                for (p: Product in cartList) {
                    item {
                        CartCard(
                            title = p.title,
                            price = p.price,
                            image = p.image
                        )
                    }
                }
            } else {
                item {
                    Text(text = stringResource(id = R.string.shopping_cart_empty_text))
                }
            }
        }
    }
}