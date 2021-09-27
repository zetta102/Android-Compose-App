package com.example.nxappc.ui.composables

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.nxappc.R

@Composable
fun UserField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    fieldLeadingIcon: ImageVector,
    isPrivate: Boolean,
    isError: Boolean
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        placeholder = { Text(placeholder) },
        leadingIcon = {
            Icon(
                painter = rememberVectorPainter(image = fieldLeadingIcon),
                contentDescription = stringResource(id = R.string.user_input_field, label)
            )
        },
        visualTransformation = if (isPrivate) PasswordVisualTransformation() else VisualTransformation.None,
        singleLine = true,
        isError = isError,
        colors = TextFieldDefaults.outlinedTextFieldColors(textColor = MaterialTheme.colors.onPrimary)
    )
}