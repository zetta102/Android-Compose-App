package com.example.nxappc.ui.composables

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.example.nxappc.R

@Composable
fun ErrorInput(isError: Boolean) {
    Text(
        text = if (isError) {
            stringResource(R.string.input_error)
        } else {
            ""
        },
        style = TextStyle(color = MaterialTheme.colors.onPrimary)
    )
}