package com.example.nxappc.ui.composables.helpers

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.nxappc.R
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState

@ExperimentalPermissionsApi
@Composable
fun PermissionsNotGrantedContent(
    doNotShowRationale: Boolean,
    permissionState: MultiplePermissionsState,
    onClick: () -> Unit
) {
    if (doNotShowRationale) {
        Text(stringResource(id = R.string.feature_not_available))
    } else {
        Column {
            Text(stringResource(id = R.string.permissions_not_granted))
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Button(onClick = { permissionState.launchMultiplePermissionRequest() }) {
                    Text(stringResource(id = R.string.dialog_yes))
                }
                Spacer(Modifier.width(8.dp))
                Button(onClick = onClick) {
                    Text(stringResource(id = R.string.dialog_no))
                }
            }
        }
    }
}