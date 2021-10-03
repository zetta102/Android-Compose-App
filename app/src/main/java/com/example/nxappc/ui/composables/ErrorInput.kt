package com.example.nxappc.ui.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.example.nxappc.R

@Composable
fun ErrorInput(isError: Boolean) {
    AnimatedVisibility(
        visible = isError,
        enter = slideInVertically { -it },
        exit = slideOutVertically { -it }) {
        Text(
            text = if (isError) {
                stringResource(R.string.input_error)
            } else {
                ""
            },
            style = TextStyle(color = MaterialTheme.colors.onPrimary)
        )
    }
}