package com.example.nxappc.ui.views.menu

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.example.nxappc.R
import com.example.nxappc.domain.data.holders.DataModel
import com.example.nxappc.domain.database.entities.Product
import com.example.nxappc.ui.composables.helpers.CartCard

@ExperimentalCoilApi
@Composable
fun ShoppingCart(dataModel: DataModel, it: PaddingValues, navData: () -> Unit) {
    val cartList = dataModel.cartProductList()
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
                        image = p.image,
                        id = p.id,
                        dataModel = dataModel
                    )
                }
            }
            item { Row (horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                Button(onClick = navData) {
                    Icon(imageVector = Icons.Default.CreditCard, contentDescription = stringResource(id = R.string.shopping_cart_creditcard_icon))
                    Text(text = stringResource(id = R.string.payment_pay_button))
                }
            } }
        } else {
            item {
                Text(text = stringResource(id = R.string.shopping_cart_empty_text))
            }
        }
    }
}
