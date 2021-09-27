package com.example.nxappc.ui.objects

sealed class Routes(val route: String) {
    object Login : Routes("login")
    object Signin : Routes("signin")
    object MainMenu : Routes("mainMenu")
    object ShoppingCart : Routes("shoppingCart")
    object Profile : Routes("userProfile")
    object ProductDetails : Routes("product/{productId}") {
        fun createRoute(productId: String) = "product/$productId"
    }
}