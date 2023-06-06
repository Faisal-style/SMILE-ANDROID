package com.example.smiletryone.screen.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.smiletryone.R
import com.example.smiletryone.ui.theme.PurplePurse
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import com.example.smiletryone.navigation.Screen


@Composable
fun HistoryScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                onBackClick = {
                    navController.popBackStack()
                    navController.clearBackStack(Screen.Home.route)
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = "Add Chat") },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.baseline_add_comment_24),
                        contentDescription = "add chat button"
                    )
                },
                onClick = { /*TODO*/ })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

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
                text = stringResource(id = R.string.history_screen),
                fontFamily = PurplePurse
            )
        }
    )
}