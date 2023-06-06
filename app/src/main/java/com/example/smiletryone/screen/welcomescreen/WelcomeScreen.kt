package com.example.smiletryone.screen.welcomescreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.smiletryone.util.OnBoardingPage
import com.example.smiletryone.R
import com.example.smiletryone.navigation.Screen
import com.example.smiletryone.ui.theme.Poppins_Bold
import com.example.smiletryone.ui.theme.Poppins_Medium
import com.example.smiletryone.ui.theme.Poppins_SemiBold
import com.example.smiletryone.viewmodel.WelcomeViewModel
import com.google.accompanist.pager.*


@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun WelcomeScreen(
    navController: NavHostController,
    welcomeViewModel: WelcomeViewModel = hiltViewModel()
) {
    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second
    )
    val pagerState = rememberPagerState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.img),
                contentScale = ContentScale.FillBounds
            ),
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            HorizontalPager(
                modifier = Modifier.weight(10f),
                count = 2,
                state = pagerState,
                verticalAlignment = Alignment.Top
            ) { position ->
                PagerScreen(onBoardingPage = pages[position])
            }
            HorizontalPagerIndicator(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .weight(1f),
                pagerState = pagerState,
            )
            FinishButton(modifier = Modifier.weight(1f), pagerState = pagerState) {
                welcomeViewModel.saveOnBoardingState(complete = true)
                navController.popBackStack()
                navController.navigate(Screen.Login.route)
            }
        }
    }

}

@Composable
fun PagerScreen(onBoardingPage: OnBoardingPage) {
    Column() {
        Box(
            modifier = Modifier
                .padding(top = 50.dp)
                .width(300.dp)
                .background(color = colorResource(id = R.color.purple_darker)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = onBoardingPage.title,
                fontSize = 26.sp,
                fontFamily = Poppins_Bold,
                color = Color.White
            )
        }
        Text(
            modifier = Modifier
                .padding(top = 12.dp, start = 21.dp),
            text = onBoardingPage.description,
            fontSize = 14.sp,
            fontFamily = Poppins_Medium,
            color = Color.Black
        )
        Image(
            painter = painterResource(id = onBoardingPage.image),
            contentDescription = null,
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(top = 77.dp)
                .size(316.dp),
        )
    }
}

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun FinishButton(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 80.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = pagerState.currentPage == 1
        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    contentColor = colorResource(
                        id = R.color.sky_button
                    )
                )
            ) {
                Text(text = "Get Started", fontFamily = Poppins_SemiBold, color = Color.White)
            }
        }

    }
}

@Composable
@Preview(showBackground = true)
fun FirstOnBoardingScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPage = OnBoardingPage.First)
    }
}