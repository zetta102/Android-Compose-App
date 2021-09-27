package com.example.nxappc.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nxappc.R
import com.example.nxappc.ui.composables.ErrorInput
import com.example.nxappc.ui.composables.UserField
import com.example.nxappc.ui.theme.Shapes

@Composable
fun RegistrationMenu(navMenuData: () -> Unit) {
    var nameBox by remember { mutableStateOf("") }
    var emailBox by remember { mutableStateOf("") }
    var phoneBox by remember { mutableStateOf("") }
    var pwdBox by remember { mutableStateOf("") }
    var nameBoxError by remember { mutableStateOf(true) }
    var emailBoxError by remember { mutableStateOf(true) }
    var phoneBoxError by remember { mutableStateOf(true) }
    var pwdBoxError by remember { mutableStateOf(true) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .padding(24.dp)
            .fillMaxSize()
    ) {
        Text(
            text = stringResource(id = R.string.signin_shop_title),
            modifier = Modifier.padding(bottom = 16.dp, top = 16.dp),
            fontSize = 24.sp,
            style = TextStyle(color = MaterialTheme.colors.onPrimary)
        )
        Button(onClick = { /*TODO*/ }) {
            Text(stringResource(id = R.string.signin_googlesignin_text))
        }
        Divider(
            modifier = Modifier.padding(bottom = 16.dp, top = 16.dp),
            color = Color.Gray
        )
        UserField(
            value = nameBox,
            onValueChange = {
                if (it.isBlank()) {
                    nameBox = ""
                    nameBoxError = true
                } else {
                    nameBox = it
                    nameBoxError = false
                }
            },
            label = stringResource(id = R.string.input_name_label),
            placeholder = "John Doe",
            fieldLeadingIcon = Icons.Default.Person,
            isPrivate = false,
            isError = nameBoxError
        )
        ErrorInput(isError = nameBoxError)
        UserField(
            value = emailBox,
            onValueChange = {
                if (it.isBlank()) {
                    emailBox = ""
                    emailBoxError = true
                } else {
                    emailBox = it
                    emailBoxError = false
                }
            },
            label = stringResource(id = R.string.input_email_label),
            placeholder = "user@domain.com",
            fieldLeadingIcon = Icons.Default.Email,
            isPrivate = false,
            isError = emailBoxError
        )
        ErrorInput(isError = emailBoxError)
        UserField(
            value = phoneBox,
            onValueChange = {
                if (it.isBlank()) {
                    phoneBox = ""
                    phoneBoxError = true
                } else {
                    phoneBox = it
                    phoneBoxError = false
                }
            },
            label = stringResource(id = R.string.input_phone_label),
            placeholder = "123456789",
            fieldLeadingIcon = Icons.Default.Phone,
            isPrivate = false,
            isError = phoneBoxError
        )
        ErrorInput(isError = phoneBoxError)
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
        ErrorInput(isError = pwdBoxError)
        Button(
            onClick = navMenuData,
            content = { Text(text = stringResource(id = R.string.signin_button)) },
            shape = Shapes.small,
            enabled = !(pwdBoxError || nameBoxError || phoneBoxError || emailBoxError),
            modifier = Modifier.padding(top = 32.dp)
        )
    }
}
