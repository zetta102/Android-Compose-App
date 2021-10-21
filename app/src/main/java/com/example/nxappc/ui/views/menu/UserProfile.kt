package com.example.nxappc.ui.views.menu

import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationManager
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.nxappc.R
import com.example.nxappc.domain.data.holders.DataModel
import com.example.nxappc.ui.composables.helpers.PermissionsNotAvailableContent
import com.example.nxappc.ui.composables.helpers.PermissionsNotGrantedContent
import com.example.nxappc.ui.composables.helpers.UserOption
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionsRequired
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@SuppressLint("MissingPermission")
@ExperimentalPermissionsApi
@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun UserProfile(
    it: PaddingValues,
    dataModel: DataModel,
    navData: () -> Unit
) {
    var doNotShowRationale by rememberSaveable { mutableStateOf(false) }
    val locationPermissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )
    )
    val user = dataModel.userData()
    val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val locationManager: LocationManager =
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    ModalBottomSheetLayout(sheetState = state, sheetContent = {
        PermissionsRequired(
            multiplePermissionsState = locationPermissionState,
            permissionsNotGrantedContent = {
                PermissionsNotGrantedContent(
                    doNotShowRationale = doNotShowRationale,
                    permissionState = locationPermissionState
                ) {
                    doNotShowRationale = true
                }
            },
            permissionsNotAvailableContent = { PermissionsNotAvailableContent() }) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Spacer(Modifier.height(20.dp))
                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                        LocationManager.NETWORK_PROVIDER
                    )
                ) {
                    Text(text = stringResource(id = R.string.payment_data_address))
                    Row(horizontalArrangement = Arrangement.Center) {
                        Text(text = "Av. Siempreviva, 2021")
                    }
                    Button(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.MyLocation,
                            contentDescription = stringResource(id = R.string.user_profile_location_icon)
                        )
                        Text(text = stringResource(id = R.string.user_profile_location_button))
                    }
                    Divider(
                        modifier = Modifier.padding(bottom = 16.dp, top = 16.dp),
                        color = Color.Gray
                    )
                    Row(horizontalArrangement = Arrangement.Center) {
                        Text(text = "Larry St., 4997")
                    }
                    Row(horizontalArrangement = Arrangement.Center) {
                        Text(text = "90 rue du Président Roosevelt")
                    }
                    Row(horizontalArrangement = Arrangement.Center) {
                        Text(text = "Luebeckertordamm, 92")
                    }
                }
            }
        }
    }) {
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = it.calculateTopPadding() + 16.dp,
                    bottom = it.calculateBottomPadding() + 16.dp
                )
        ) {
            Image(
                painter = rememberImagePainter(user.avatar),
                contentDescription = null, // TODO: 10/10/2021
                modifier = Modifier
                    .width(96.dp)
                    .height(144.dp)
            )
            Text(text = user.first_name + " " + user.last_name)
            Text(text = user.email)
            UserOption(
                title = stringResource(id = R.string.user_profile_locations),
                icon = Icons.Default.LocationOn,
                onClick = {
                    scope.launch { state.show() }
                    locationPermissionState.launchMultiplePermissionRequest()
                }
            )
            UserOption(
                title = stringResource(id = R.string.user_profile_payment_methods),
                icon = Icons.Default.CreditCard
            )
            UserOption(
                title = stringResource(id = R.string.user_profile_orders),
                icon = Icons.Default.Restore
            )
            UserOption(
                title = stringResource(id = R.string.user_profile_notifications),
                icon = Icons.Default.Notifications
            )
            UserOption(
                title = stringResource(id = R.string.user_profile_change_password),
                icon = Icons.Default.Lock
            )
            Button(onClick = navData , modifier = Modifier.fillMaxWidth()) {
                Icon(
                    imageVector = Icons.Default.PowerSettingsNew,
                    contentDescription = stringResource(id = R.string.user_profile_logout_icon)
                )
                Text(text = stringResource(id = R.string.logout_button))
            }
        }
    }
}
