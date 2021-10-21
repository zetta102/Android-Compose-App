package com.example.nxappc.ui.views.premenu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nxappc.R
import com.example.nxappc.ui.composables.helpers.UserField
import com.example.nxappc.ui.theme.Shapes

@Composable
fun LoginMenu(navRegData: () -> Unit, navMenuData: () -> Unit) {
    var usrBox by remember { mutableStateOf("") }
    var pwdBox by remember { mutableStateOf("") }
    var usrBoxError by remember { mutableStateOf(true) }
    var pwdBoxError by remember { mutableStateOf(true) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.shop_logo),
            contentDescription = stringResource(id = R.string.login_shop_icon),
            modifier = Modifier
                .padding(top = 80.dp, bottom = 16.dp)
                .height(128.dp)
                .width(128.dp)
        )
        Text(
            text = stringResource(id = R.string.login_shop_title),
            modifier = Modifier.padding(bottom = 16.dp, top = 16.dp),
            fontSize = 24.sp,
            style = TextStyle(color = MaterialTheme.colors.onPrimary)
        )
        UserField(
            value = usrBox,
            onValueChange = {
                if (it.isBlank()) {
                    usrBox = ""
                    usrBoxError = true
                } else {
                    usrBox = it
                    usrBoxError = false
                }
            },
            label = stringResource(id = R.string.input_user_label),
            placeholder = "user",
            fieldLeadingIcon = Icons.Default.Person,
            isPrivate = false,
            isError = usrBoxError
        )
        UserField(
            value = pwdBox,
            onValueChange = {
                if (it.isBlank()) {
                    pwdBox = ""
                    pwdBoxError = true
                } else {
                    pwdBox = it
                    pwdBoxError = false
                }
            },
            label = stringResource(id = R.string.input_password_label),
            placeholder = "123Password",
            fieldLeadingIcon = Icons.Default.Lock,
            isPrivate = true,
            isError = pwdBoxError
        )
        Button(
            onClick = navMenuData,
            content = { Text(text = stringResource(id = R.string.login_button)) },
            shape = Shapes.small,
            enabled = !(pwdBoxError || usrBoxError),
            modifier = Modifier.padding(top = 48.dp)
        )
        TextButton(
            onClick = navRegData,
            content = {
                Text(
                    text = stringResource(id = R.string.signin_button),
                    color = MaterialTheme.colors.onPrimary
                )
            },
            modifier = Modifier.padding(top = 32.dp)
        )
    }
}
