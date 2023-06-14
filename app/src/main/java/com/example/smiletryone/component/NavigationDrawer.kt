package com.example.smiletryone.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.smiletryone.R
import com.example.smiletryone.data.remote.responses.DetailUserResponse
import com.example.smiletryone.navigation.Screen
import com.example.smiletryone.ui.theme.Monda_Bold
import com.example.smiletryone.ui.theme.Monda_Regular
import com.example.smiletryone.ui.theme.PurplePurse
import com.example.smiletryone.util.Resource
import com.example.smiletryone.viewmodel.HomeViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MyTopAppBar(onMenuClick: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { onMenuClick() }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = stringResource(id = R.string.menu)
                )
            }
        },
        title = {
            Text(text = stringResource(id = R.string.app_name), fontFamily = PurplePurse)
        }
    )
}

@Composable
fun MyNavDrawerContent(
    modifier: Modifier = Modifier,
    onItemSelected: (title: String) -> Unit,
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val detailUserInfo =
        produceState<Resource<DetailUserResponse>>(initialValue = Resource.Loading()) {
            value = homeViewModel.getUserDetailInfo()
        }.value
    var image by rememberSaveable {
        mutableStateOf("https://img.freepik.com/free-vector/businessman-character-avatar-isolated_24877-60111.jpg?w=2000")
    }
    if (detailUserInfo.data?.userResult?.image != null) {
        image = detailUserInfo.data.userResult.image.toString()
    }
    val scope = rememberCoroutineScope()
    val isLoading by remember { homeViewModel.isLoading }
    val items = listOf(
        MenuItem(
            title = stringResource(id = R.string.profile),
            icon = Icons.Default.Person,
            route = Screen.DetailUserScreen.route
        ),
        MenuItem(
            title = stringResource(id = R.string.history),
            icon = ImageVector.vectorResource(id = R.drawable.baseline_history_24),
            route = Screen.History.route
        ),
        MenuItem(
            title = stringResource(id = R.string.logout),
            icon = ImageVector.vectorResource(id = R.drawable.baseline_logout_24),
            route = Screen.Login.route
        ),
    )

    Column(
        modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .height(220.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colors.primary)
                .clickable { navController.navigate(Screen.DetailUserScreen.route) },
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp),
                horizontalAlignment = Alignment.Start
            ) {
                AsyncImage(
                    model = image,
                    contentDescription = "Avtar Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(110.dp)
                        .clip(CircleShape)

                )

                detailUserInfo.data?.userResult.let {
                    if (it != null) {
                        Text(
                            text = it.email,
                            fontSize = 20.sp,
                            color = Color.White,
                            fontFamily = Monda_Bold,
                            modifier = Modifier
                                .padding(top = 8.dp)
                        )
                    }
                }


                detailUserInfo.data?.userResult.let {
                    if (it != null) {
                        Text(
                            text = it.userName,
                            fontSize = 15.sp,
                            color = Color.White,
                            fontFamily = Monda_Regular,
                        )
                    }
                }

            }


        }
        for (item in items) {
            Row(
                modifier = Modifier
                    .clickable {
                        if (item.title == "Logout") {
                            onItemSelected(item.title)
                            scope.launch {
                                homeViewModel.logout()
                                navController.popBackStack()
                                navController.navigate(homeViewModel.route.value)
                            }
                        } else if (item.title == "Profile") {
                            onItemSelected(item.title)
                            navController.navigate(item.route)
                        } else {
                            onItemSelected(item.title)
                            navController.navigate(item.route)
                        }
                    }
                    .padding(vertical = 12.dp, horizontal = 16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = item.icon, contentDescription = item.title, tint = Color.Black)
                Spacer(modifier = Modifier.width(32.dp))
                Text(text = item.title, fontFamily = Monda_Regular)

            }
        }
        Divider()
    }
}
