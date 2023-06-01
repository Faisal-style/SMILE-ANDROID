package com.example.smiletryone.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.smiletryone.R
import com.example.smiletryone.component.MyNavDrawerContent
import com.example.smiletryone.component.MyTopAppBar
import kotlinx.coroutines.launch
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun HomeScreen() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            MyTopAppBar(
                onMenuClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        drawerContent = {
            MyNavDrawerContent(onItemSelected = {})
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text(stringResource(R.string.Hello_World))
        }

    }
}


@Composable
@Preview(showBackground = true)
fun AppBarPreview(){
    HomeScreen()
}