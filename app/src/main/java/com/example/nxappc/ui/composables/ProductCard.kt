package com.example.nxappc.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter

@ExperimentalCoilApi
@Composable
fun ProductCard(
    title: String,
    price: Double,
    image: String,
    onClick: () -> Unit
) {
    val ratingNumber = (1..1000).random()
    Row {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick),
            backgroundColor = MaterialTheme.colors.background
        ) {
            Row {
                Image(painter = rememberImagePainter(image), contentDescription = title, modifier = Modifier.width(96.dp).height(144.dp))
                Column(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(start = 24.dp)) {
                    Text(text = title, fontSize = 4.em)
                    Text(text = "$${price}")
                    Row {
                        run { RatingGenerator() }
                        Text(
                            text = ratingNumber.toString(),
                            modifier = Modifier.padding(start = 12.dp)
                        )
                    }
                }
            }
        }
    }
}

