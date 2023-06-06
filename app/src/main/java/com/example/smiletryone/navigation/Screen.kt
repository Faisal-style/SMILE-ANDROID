package com.example.smiletryone.navigation

sealed class Screen(val route: String){
    object Welcome: Screen(route = "welcome_screen")
    object Home: Screen(route = "home_screen")
    object History: Screen(route = "history_screen")
    object Login: Screen(route = "login_screen")
    object Register: Screen(route = "register_screen")
    object SplashScreen: Screen(route = "splash_screen")
}
