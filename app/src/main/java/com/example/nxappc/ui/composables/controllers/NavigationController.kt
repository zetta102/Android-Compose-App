package com.example.nxappc.ui.composables.controllers

import androidx.compose.animation.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavDeepLink
import androidx.navigation.NavDestination.Companion.hierarchy
import coil.annotation.ExperimentalCoilApi
import com.example.nxappc.MainActivity
import com.example.nxappc.domain.data.holders.DataModel
import com.example.nxappc.domain.database.ShopDatabase
import com.example.nxappc.domain.misc.Routes
import com.example.nxappc.ui.composables.layout.BottomNavigationBar
import com.example.nxappc.ui.composables.layout.TopAppBar
import com.example.nxappc.ui.views.menu.*
import com.example.nxappc.ui.views.premenu.LoginMenu
import com.example.nxappc.ui.views.premenu.RegistrationMenu
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.android.gms.location.FusedLocationProviderClient

@ExperimentalPermissionsApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@ExperimentalCoilApi
@Composable
fun NavigationController(
    db: ShopDatabase,
    builder: NotificationCompat.Builder
) {
    var isMenu by remember { mutableStateOf(false) }
    var isProductDetail by remember { mutableStateOf(false) }
    val navController = rememberAnimatedNavController()
    db.clearAllTables()
    val productModel = DataModel(db)
    productModel.preloadProducts()
    Scaffold(
        topBar = {
            if (isMenu || isProductDetail) {
                TopAppBar()
            }
        },
        bottomBar = {
            if (isMenu) {
                BottomNavigationBar(navController = navController)
            }
        }
    ) {
        val pdValues = it
        AnimatedNavHost(navController = navController, startDestination = Routes.Login.toString(),
            enterTransition = { _, _ -> slideInVertically { it } },
            exitTransition = { _, _ -> slideOutVertically { it } }) {
            composable(route = Routes.Login.toString(), enterTransition = { initial, target ->
                if ((target.destination.hierarchy.any { it.route == Routes.Signin.toString() }) || (initial.destination.hierarchy.any { it.route == Routes.Signin.toString() })) {
                    slideInHorizontally { -it }
                } else {
                    slideInVertically { it }
                }
            }, exitTransition = { _, target ->
                if (target.destination.hierarchy.any { it.route == Routes.Signin.toString() }) {
                    slideOutHorizontally { -it }
                } else {
                    slideOutVertically { it }
                }
            }) {
                isMenu = false
                isProductDetail = false
                LoginMenu(
                    navRegData = { navController.navigate(Routes.Signin.toString()) },
                    navMenuData = { navController.navigate(Routes.MainMenu.toString()) })
            }
            composable(route = Routes.Signin.toString(), enterTransition = { initial, _ ->
                if (initial.destination.hierarchy.any { it.route == Routes.Login.toString() }) {
                    slideInHorizontally { it }
                } else {
                    slideInVertically { it }
                }
            }, exitTransition = { _, target ->
                if (target.destination.hierarchy.any { it.route == Routes.MainMenu.toString() }) {
                    slideOutVertically { it }
                } else {
                    slideOutHorizontally { it }
                }
            }) {
                isMenu = false
                isProductDetail = false
                RegistrationMenu(navMenuData = { navController.navigate(Routes.MainMenu.toString()) })
            }
            composable(
                route = Routes.MainMenu.toString()
            ) {
                isMenu = true
                isProductDetail = false
                MainMenu(navController = navController, dataModel = productModel, it = pdValues)
            }
            composable(route = Routes.ShoppingCart.toString()) {
                isMenu = true
                isProductDetail = false
                ShoppingCart(dataModel = productModel, it = pdValues, navData = {
                    navController.navigate(
                        Routes.Payment.toString()
                    )
                })
            }
            composable(route = Routes.Profile.toString()) {
                isMenu = true
                isProductDetail = false
                UserProfile(it = pdValues, dataModel = productModel, navData = {navController.navigate(Routes.Login.toString())})
            }
            composable(
                route = "product/{productId}"
            )
            {
                isMenu = false
                isProductDetail = true
                val productId = it.arguments?.getString("productId")
                requireNotNull(productId) { "That product does not exist, please try again." }
                ProductDetail(
                    productId,
                    navController = navController,
                    dataModel = productModel,
                    it = pdValues
                )
            }
            composable(
                route = Routes.Payment.toString()
            ) {
                isMenu = false
                isProductDetail = false
                PaymentDetail(
                    navData = { navController.navigate(Routes.PaymentFinished.toString()) },
                    dataModel = productModel
                )
            }
            composable(
                route = Routes.PaymentFinished.toString(),
                deepLinks = listOf(NavDeepLink("myapp://${Routes.PaymentFinished}"))
            ) {
                isMenu = false
                isProductDetail = false
                with(NotificationManagerCompat.from(LocalContext.current)) {
                    notify(0, builder.build())
                }
                FinishedPayment(navData = { navController.navigate(Routes.MainMenu.toString()) })
            }
        }
    }
}