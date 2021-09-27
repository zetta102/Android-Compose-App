package com.example.nxappc.ui.composables

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat.startActivity
import com.example.nxappc.R

@Composable
fun TopAppBar() {
    val context = LocalContext.current
    val string = stringResource(id = R.string.disabled_search)
    TopAppBar(title = { Text(text = stringResource(R.string.appbar_text)) }, actions = {
        IconButton(onClick = { Toast.makeText(context, string, Toast.LENGTH_SHORT).show() }) {
            Icon(
                painter = rememberVectorPainter(image = Icons.Default.Search),
                contentDescription = stringResource(R.string.appbar_search_icon)
            )
        }
        IconButton(onClick = {
            startActivity(
                context,
                Intent(Intent.ACTION_VIEW, Uri.parse("https://www.bedu.org")),
                null
            )
        }) {
            Icon(
                painter = rememberVectorPainter(image = Icons.Default.Info),
                contentDescription = stringResource(R.string.appbar_info_button)
            )
        }
    })
}