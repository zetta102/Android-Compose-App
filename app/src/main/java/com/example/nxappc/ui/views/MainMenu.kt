package com.example.nxappc.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.example.nxappc.ui.composables.BottomNavigationBar
import com.example.nxappc.ui.composables.ProductCard
import com.example.nxappc.ui.composables.TopAppBar
import com.example.nxappc.ui.objects.Product
import com.example.nxappc.ui.objects.Routes
import com.example.nxappc.ui.state.ProductModel
import com.example.nxappc.ui.theme.NXAppCTheme

@ExperimentalCoilApi
@Composable
fun MainMenu(navController: NavController, productModel: ProductModel) {
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
            for (p: Product in productModel.productList) {
                item {
                    ProductCard(
                        title = p.title,
                        price = p.price,
                        image = p.image,
                        onClick = { navController.navigate(Routes.ProductDetails.createRoute(p.id.toString())) }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NXAppCTheme {
    }
}