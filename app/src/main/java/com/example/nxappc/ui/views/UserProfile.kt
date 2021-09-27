package com.example.nxappc.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.nxappc.ui.composables.BottomNavigationBar
import com.example.nxappc.ui.composables.TopAppBar

@Composable
fun UserProfile(navController: NavController) {
    Scaffold(
        topBar = { TopAppBar() },
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) {
        Column {
            Text(text = "TBD")
        }
    }
}
