package com.example.nxappc.ui.composables.layout

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.nxappc.R
import com.example.nxappc.domain.misc.Routes

@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val views = listOf(
        Triple(
            stringResource(id = R.string.bottombar_main_icon),
            Icons.Default.Home,
            Routes.MainMenu.toString()
        ),
        Triple(
            stringResource(id = R.string.bottombar_cart_icon),
            Icons.Default.ShoppingCart,
            Routes.ShoppingCart.toString()
        ),
        Triple(
            stringResource(id = R.string.bottombar_profile_icon),
            Icons.Default.Person,
            Routes.Profile.toString()
        )
    )
    BottomNavigation {
        views.forEachIndexed { index, view ->
            BottomNavigationItem(
                selected = currentDestination?.hierarchy?.any { it.route == view.third } == true,
                onClick = {
                    navController.navigate(views[index].third) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = rememberVectorPainter(image = views[index].second),
                        contentDescription = views[index].first
                    )
                },
                label = {
                    Text(text = if (currentDestination?.hierarchy?.any { it.route == view.third } == true) {
                        views[index].first
                    } else {
                        ""
                    })
                })
        }
    }
}