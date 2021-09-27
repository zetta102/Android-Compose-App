package com.example.nxappc.ui.composables

import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.nxappc.R

@Composable
fun RatingGenerator() {
    val number = (1..5).random()
    for (i in 1..number) {
        Icon(
            Icons.Default.Star,
            contentDescription = stringResource(id = R.string.rating_icon_filled),
            tint = MaterialTheme.colors.secondary
        )
    }
    for (i in number until 5) {
        Icon(
            painterResource(id = R.drawable.ic_baseline_star_outline_24),
            contentDescription = stringResource(id = R.string.rating_icon_outlined),
            tint = MaterialTheme.colors.secondary
        )
    }
}