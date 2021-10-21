package com.example.nxappc

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.room.Room
import coil.annotation.ExperimentalCoilApi
import com.example.nxappc.domain.database.ShopDatabase
import com.example.nxappc.domain.misc.CreateNotificationBuilder
import com.example.nxappc.domain.misc.SetNotificationChannel
import com.example.nxappc.ui.composables.controllers.NavigationController
import com.example.nxappc.ui.theme.NXAppCTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@ExperimentalCoilApi
@ExperimentalPermissionsApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = Room.databaseBuilder(
            applicationContext,
            ShopDatabase::class.java, "ShopDB"
        ).allowMainThreadQueries().build()
        val builder = CreateNotificationBuilder(this, "testChannel", getString(R.string.notification_builder_title), getString(R.string.notification_builder_content))
        @RequiresApi(Build.VERSION_CODES.O)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            SetNotificationChannel(this, "testChannel")
        }
        setContent {
            NXAppCTheme {
                NavigationController(db, builder)
            }
        }
    }
}