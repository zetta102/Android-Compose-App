package com.example.nxappc.ui.composables

import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination.Companion.hierarchy
import coil.annotation.ExperimentalCoilApi
import com.example.nxappc.ui.objects.Routes
import com.example.nxappc.ui.state.ProductModel
import com.example.nxappc.ui.views.*
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@ExperimentalAnimationApi
@ExperimentalCoilApi
@Composable
fun NavigationController() {
    val navController = rememberAnimatedNavController()
    val productModel = ProductModel()
    AnimatedNavHost(navController = navController, startDestination = Routes.Login.toString()) {
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
            RegistrationMenu(navMenuData = { navController.navigate(Routes.MainMenu.toString()) })
        }
        composable(
            route = Routes.MainMenu.toString(),
            enterTransition = { _, _ -> slideInVertically { it } },
            exitTransition = { _, _ -> slideOutVertically { it } }) {
            MainMenu(navController = navController, productModel = productModel)
        }
        composable(route = Routes.ShoppingCart.toString(),
            enterTransition = { _, _ -> slideInVertically { it } },
            exitTransition = { _, _ -> slideOutVertically { it } }) {
            ShoppingCart(navController = navController, productModel = productModel)
        }
        composable(route = Routes.Profile.toString(),
            enterTransition = { _, _ -> slideInVertically { it } },
            exitTransition = { _, _ -> slideOutVertically { it } }) {
            UserProfile(navController = navController)
        }
        composable(
            route = "product/{productId}",
            enterTransition = { _, _ -> slideInVertically { it } },
            exitTransition = { _, _ -> slideOutVertically { it } })
        {
            val productId = it.arguments?.getString("productId")
            requireNotNull(productId) { "That product does not exist, please try again." }
            ProductDetail(productId, navController = navController, productModel = productModel)
        }
    }
}