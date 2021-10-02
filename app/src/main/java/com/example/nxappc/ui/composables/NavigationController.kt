package com.example.nxappc.ui.composables

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
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
        composable(route = Routes.Login.toString()) {
            LoginMenu(
                navRegData = { navController.navigate(Routes.Signin.toString()) },
                navMenuData = { navController.navigate(Routes.MainMenu.toString()) })
        }
        composable(route = Routes.Signin.toString()) {
            RegistrationMenu(navMenuData = { navController.navigate(Routes.MainMenu.toString()) })
        }
        composable(route = Routes.MainMenu.toString()) {
            MainMenu(navController = navController, productModel = productModel)
        }
        composable(
            route = "product/{productId}"
        ) {
            val productId = it.arguments?.getString("productId")
            requireNotNull(productId) { "That product does not exist, please try again." }
            ProductDetail(productId, navController = navController, productModel = productModel)
        }
        composable(route = Routes.Profile.toString()) {
            UserProfile(navController = navController)
        }
        composable(route = Routes.ShoppingCart.toString()) {
            ShoppingCart(navController = navController, productModel = productModel)
        }
    }
}