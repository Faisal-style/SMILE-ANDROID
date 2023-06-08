package com.example.smiletryone.navigation


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.smiletryone.screen.history.HistoryScreen
import com.example.smiletryone.screen.home.HomeScreen
import com.example.smiletryone.screen.login.LoginScreen
import com.example.smiletryone.screen.register.RegisterScreen
import com.example.smiletryone.screen.splasshscreen.SplashScreen
import com.example.smiletryone.screen.welcomescreen.WelcomeScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: String,
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Screen.Home.route){
            HomeScreen(navController)
        }
        composable(route = Screen.History.route){
            HistoryScreen(navController)
        }
        composable(route = Screen.Login.route){
            LoginScreen(navController)
        }
        composable(route = Screen.Welcome.route){
            WelcomeScreen(navController = navController)
        }
        composable(route = Screen.Register.route){
            RegisterScreen(navController)
        }
        composable(route = Screen.SplashScreen.route){
            SplashScreen()
        }
        
    }
}