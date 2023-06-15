package com.example.smiletryone.screen.detail

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.smiletryone.R
import com.example.smiletryone.component.GifSmile
import com.example.smiletryone.data.remote.responses.DetailUserResponse
import com.example.smiletryone.navigation.Screen
import com.example.smiletryone.ui.theme.Monda_Regular
import com.example.smiletryone.ui.theme.PurplePurse
import com.example.smiletryone.util.Resource
import com.example.smiletryone.viewmodel.HomeViewModel

@Composable
fun DetailUserScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    BackHandler() {
        navController.navigate(Screen.Home.route){
            popUpTo(navController.graph.id){
                inclusive = true
            }
        }
    }
    val detailUserInfo =
        produceState<Resource<DetailUserResponse>>(initialValue = Resource.Loading()) {
            value = homeViewModel.getUserDetailInfo()
        }.value
    val name = detailUserInfo.data?.userResult?.userName
    val email = detailUserInfo.data?.userResult?.email
    var image by rememberSaveable {
        mutableStateOf("https://img.freepik.com/free-vector/businessman-character-avatar-isolated_24877-60111.jpg?w=2000")
    }
    val isLoading by remember { homeViewModel.isLoading }
    if (detailUserInfo.data?.userResult?.image != null) {
        image = detailUserInfo.data.userResult.image
    }

    Box() {
        Scaffold(
            topBar = {
                TopAppBar(
                    onBackClick = {
                        navController.popBackStack()
                        navController.navigate(Screen.Home.route)
                    }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = modifier.size(30.dp))
                AsyncImage(
                    model = image,
                    contentDescription = "Avatar Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(200.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.size(30.dp))
                if (name != null) {
                    Text(
                        text = name,
                        fontFamily = Monda_Regular,
                        fontSize = 30.sp,
                        color = Color.Black
                    )
                }
                Spacer(modifier = Modifier.size(30.dp))
                if (email != null) {
                    Text(
                        text = email,
                        fontFamily = Monda_Regular,
                        fontSize = 15.sp,
                        color = Color.Black
                    )
                }
                Spacer(modifier = Modifier.size(50.dp))
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier,
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                ) {
                    Text(
                        text = "Logout",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                        color = Color.White
                    )
                }
            }
        }
        if (isLoading){
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .wrapContentSize(Alignment.Center)
            ) {
                GifSmile(modifier = Modifier.size(100.dp))
            }
        }
    }
}

@Composable
fun TopAppBar(onBackClick: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {
                onBackClick()
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.menu)
                )
            }
        },
        title = {
            Text(
                text = stringResource(id = R.string.detail_user),
                fontFamily = PurplePurse
            )
        }
    )
}