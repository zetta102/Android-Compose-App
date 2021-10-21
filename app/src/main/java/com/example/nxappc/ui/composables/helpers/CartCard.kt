package com.example.nxappc.ui.composables.helpers

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.nxappc.domain.data.holders.DataModel

@ExperimentalCoilApi
@Composable
fun CartCard(
    title: String,
    price: Double,
    image: String,
    id: Int,
    dataModel: DataModel
) {
    var quantity by remember { mutableStateOf(1) }
    Row {
        Card(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = MaterialTheme.colors.background
        ) {
            Row {
                Image(
                    painter = rememberImagePainter(image),
                    contentDescription = title,
                    modifier = Modifier
                        .width(96.dp)
                        .height(144.dp)
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(start = 24.dp)
                ) {
                    Text(text = title, fontSize = 4.em)
                    Text(text = "$${price}")
                    Row {
                        IconButton(onClick = {
                            if (quantity > 1) {
                                quantity--
                                dataModel.addOrRemoveFromCart(id.toString(), quantity)
                            }
                        }) {
                            Text(text = "-")
                        }
                        Text(text = quantity.toString())
                        IconButton(onClick = {
                            quantity++
                            dataModel.addOrRemoveFromCart(id.toString(), quantity)
                        }) {
                            Text(text = "+")
                        }
                    }
                }
            }
        }
    }
}
