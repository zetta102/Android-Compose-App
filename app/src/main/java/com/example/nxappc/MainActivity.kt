package com.example.nxappc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import coil.annotation.ExperimentalCoilApi
import com.example.nxappc.ui.composables.NavigationController
import com.example.nxappc.ui.theme.NXAppCTheme

class MainActivity : ComponentActivity() {
    @ExperimentalCoilApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NXAppCTheme {
                NavigationController()
            }
        }
    }
}


@ExperimentalCoilApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NXAppCTheme {
        NavigationController()
    }
}