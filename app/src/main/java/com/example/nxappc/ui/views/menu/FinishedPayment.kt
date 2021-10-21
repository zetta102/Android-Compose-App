package com.example.nxappc.ui.views.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.nxappc.R

@Composable
fun FinishedPayment(navData: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Image(
            painterResource(id = R.drawable.img_package),
            contentDescription = stringResource(id = R.string.finished_payment_icon)
        )
        Text(text = stringResource(id = R.string.finished_payment_thanks))
        Text(text = stringResource(id = R.string.finished_payment_clarification))
        Button(onClick = navData) {
            Text(text = stringResource(id = R.string.finished_payment_menu))
        }
    }

}