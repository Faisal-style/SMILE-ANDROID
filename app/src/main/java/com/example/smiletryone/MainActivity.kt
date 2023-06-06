package com.example.smiletryone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.smiletryone.navigation.Screen
import com.example.smiletryone.navigation.SetupNavGraph
import com.example.smiletryone.screen.history.HistoryScreen
import com.example.smiletryone.screen.home.HomeScreen
import com.example.smiletryone.screen.welcomescreen.PagerScreen
import com.example.smiletryone.screen.welcomescreen.WelcomeScreen
import com.example.smiletryone.ui.theme.SMILETRYONETheme
import com.example.smiletryone.util.OnBoardingPage
import com.example.smiletryone.viewmodel.SplashViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalAnimationApi
@ExperimentalPagerApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var splashViewModel: SplashViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition{
            !splashViewModel.isLoading.value
        }
        setContent {
            SMILETRYONETheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val screen by splashViewModel.startDestination
                    val navController = rememberNavController()
                    SetupNavGraph(navController = navController, startDestination = screen)
                }
            }
        }
    }
}
