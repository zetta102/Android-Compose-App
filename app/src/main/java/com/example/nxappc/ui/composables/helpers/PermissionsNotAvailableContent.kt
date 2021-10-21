package com.example.nxappc.ui.composables.helpers

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.nxappc.R

@Composable
fun PermissionsNotAvailableContent() {
    Column {
        Text(
            stringResource(id = R.string.permissions_not_available)
        )
    }
}
