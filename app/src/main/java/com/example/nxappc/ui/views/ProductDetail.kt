package com.example.nxappc.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.nxappc.R
import com.example.nxappc.ui.composables.RatingGenerator
import com.example.nxappc.ui.composables.TopAppBar
import com.example.nxappc.ui.objects.Routes
import com.example.nxappc.ui.state.ProductModel
import kotlin.math.roundToInt

@ExperimentalCoilApi
@Composable
fun ProductDetail(
    id: String?,
    productModel: ProductModel,
    navController: NavController
) {
    val product = productModel.productDetailData(id)
    val productPerMonth = (product.price / 6).roundToInt()
    val ratingNumber = (1..1000).random()
    Scaffold(
        topBar = { TopAppBar() }
    ) {
        Column(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = it.calculateTopPadding() + 16.dp,
                    bottom = it.calculateBottomPadding() + 16.dp
                )
                .verticalScroll(ScrollState(0)),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = stringResource(id = R.string.product_detail_new))
            Text(text = product.title, fontSize = 8.em)
            Row {
                run { RatingGenerator() }
                Text(
                    text = ratingNumber.toString(),
                    modifier = Modifier.padding(start = 12.dp)
                )
            }
            Image(
                painter = rememberImagePainter(product.image),
                contentDescription = product.title,
                modifier = Modifier
                    .width(256.dp)
                    .height(256.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(text = "$${product.price}")
            Text(text = stringResource(id = R.string.product_detail_paypermonth, productPerMonth))
            Row {
                Icon(
                    Icons.Default.LocationOn,
                    contentDescription = stringResource(id = R.string.product_detail_location_icon)
                )
                Text(text = stringResource(id = R.string.product_detail_location_text))
            }
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.ic_directions_car_24),
                    contentDescription = stringResource(id = R.string.product_detail_car_icon)
                )
                Text(text = stringResource(id = R.string.product_detail_car_text))
            }
            Button(
                onClick = {
                    productModel.addToCart(product.id.toString())
                    navController.navigate(Routes.ShoppingCart.toString())
                },
                enabled = true,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = stringResource(id = R.string.product_detail_addtocart_button))
            }
            Text(text = stringResource(id = R.string.product_detail_description_text))
            Text(text = product.description)
        }
    }
}
