package com.example.nxappc.domain.misc

sealed class Routes(val route: String) {
    object Login : Routes("login")
    object Signin : Routes("signin")
    object MainMenu : Routes("mainMenu")
    object ShoppingCart : Routes("shoppingCart")
    object Profile : Routes("userProfile")
    object ProductDetails : Routes("product") {
        fun createRoute(productId: String) = "product/$productId"
    }
    object Payment : Routes("paymentDetail")
    object PaymentFinished : Routes("finishedPayment")
}