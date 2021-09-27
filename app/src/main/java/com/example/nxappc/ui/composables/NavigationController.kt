package com.example.nxappc.ui.composables

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.example.nxappc.ui.objects.Routes
import com.example.nxappc.ui.state.ProductModel
import com.example.nxappc.ui.views.*

@ExperimentalCoilApi
@Composable
fun NavigationController() {
    val navController = rememberNavController()
    val productModel = ProductModel()
    NavHost(navController = navController, startDestination = Routes.Login.toString()) {
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