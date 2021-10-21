package com.example.nxappc.ui.views.menu

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.nxappc.R
import com.example.nxappc.domain.data.holders.DataModel
import com.example.nxappc.ui.composables.helpers.UserOption


@Composable
fun PaymentDetail(navData: () -> Unit, dataModel: DataModel) {
    val productTotal = dataModel.getCost()
    val deliveryCost = 30.00
    val totalCost = deliveryCost + productTotal
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Text(text = stringResource(id = R.string.payment_data_current))
        UserOption(title = stringResource(id = R.string.payment_data_address), icon = Icons.Default.LocationOn)
        UserOption(title = stringResource(id = R.string.payment_data_method), icon = Icons.Default.CreditCard)
        Text(text = stringResource(id = R.string.payment_data_overview))
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            Text(text = stringResource(id = R.string.payment_data_subtotal))
            Text(text = "$$productTotal")
        }
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            Text(text = stringResource(id = R.string.payment_data_delivery))
            Text(text = "$$deliveryCost")
        }
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            Text(text = stringResource(id = R.string.payment_data_total))
            Text(text = "$$totalCost")
        }
        Button(onClick = navData) {
            Text(text = stringResource(id = R.string.payment_pay_button))
        }
    }
}