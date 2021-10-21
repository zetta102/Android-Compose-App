package com.example.nxappc.ui.views.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.example.nxappc.domain.database.entities.Product
import com.example.nxappc.domain.misc.Routes
import com.example.nxappc.domain.data.holders.DataModel
import com.example.nxappc.ui.composables.helpers.ProductCard
import com.example.nxappc.ui.theme.NXAppCTheme

@ExperimentalCoilApi
@Composable
fun MainMenu(navController: NavController, dataModel: DataModel, it: PaddingValues) {
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
        for (p: Product in dataModel.productList()) {
            item {
                ProductCard(
                    title = p.title,
                    price = p.price,
                    image = p.image,
                    onCardClick = { navController.navigate(Routes.ProductDetails.createRoute(p.id.toString())) }
                )
            }
        }
    }
}